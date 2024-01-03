package `in`.gov.edudel.smcapp.models

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.gov.edudel.smcapp.api
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel(){
    val loginType = MutableStateFlow(LoginType.Member)
    val loginId = MutableStateFlow("")
    val showOtpDialog = MutableStateFlow(false)

    private val otp = MutableStateFlow<String?>(null)

    var uiState = MutableStateFlow<UIState>(UIState.Success)

    fun getLoginUser(): User?{
        return User(0,"", "", "", "", "", "", "")
    }

    fun handleLogin(loginId: String, loginType: LoginType) = viewModelScope.launch{
        uiState.value = UIState.Loading("Getting User Profile")
        val mobile = when(loginType){
            LoginType.Teacher, LoginType.HOS -> {
                // todo: get teacher profile from arun's and number therefrom
                "1234"
            }
            LoginType.Zone, LoginType.District -> {
                // todo: get zone/dist mobile number from pankaj's api
                "1234"
            }
            else -> {
                // todo: fetch user profile from praveen's api
                if(loginId=="4321") loginId
                else null
            }
        }

        if(mobile == null){
            uiState.value = UIState.Error("Invalid user ID")
            return@launch
        }

        uiState.value = UIState.Loading("Sending OTP")
        otp.value = api.sendOtp(mobile)
        if(otp.value == null){
            uiState.value = UIState.Error("Error: OTP sending failed")
            return@launch
        }
        uiState.value = UIState.Success

        showOtpDialog.value = true
    }

    val otpValidated = MutableStateFlow(false)
    fun handleOtp(enteredOtp: String) = viewModelScope.launch {
        if(otp.value == enteredOtp){
            otpValidated.value = true
        } else {
            uiState.value = UIState.Error("Wrong OTP")
            otpValidated.value = false
        }
    }



}