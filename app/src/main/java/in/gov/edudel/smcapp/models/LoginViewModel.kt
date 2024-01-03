package `in`.gov.edudel.smcapp.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import `in`.gov.edudel.smcapp.api
import `in`.gov.edudel.smcapp.ui.screens.OTP
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel(){
    val loginType = MutableStateFlow(LoginType.Member)
    val loginId = MutableStateFlow("")
    val showOtpDialog = MutableStateFlow(false)

    var uiState = MutableStateFlow<UIState>(UIState.Loading())

    fun handleLogin(loginId: String, loginType: LoginType) = viewModelScope.launch{
        uiState.value = UIState.Loading("Getting User Profile")
        val mobile = when(loginType){
            LoginType.Teacher, LoginType.HOS -> {
                // todo: get teacher profile from arun's and number therefrom
                "123"
            }
            LoginType.Zone, LoginType.District -> {
                // todo: get zone/dist mobile number from pankaj's api
                "456"
            }
            else -> {
                // todo: fetch user profile from praveen's api
                if(loginId=="4321") loginId
                else null
            }
        }

        if(mobile == null){
            uiState.value = UIState.Error("Invalid user ID")
            cancel()
        }

        uiState.value = UIState.Loading("Sending OTP")
        OTP = api.sendOtp(mobile!!)
        if(OTP == null){
            uiState.value = UIState.Error("Error: OTP sending failed")
            cancel()
        }
        showOtpDialog.value = true
    }



}