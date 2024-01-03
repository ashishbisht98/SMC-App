package `in`.gov.edudel.smcapp.ui.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusProperties
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.gov.edudel.smcapp.models.LoginType
import `in`.gov.edudel.smcapp.models.LoginViewModel
import `in`.gov.edudel.smcapp.models.UIState
import `in`.gov.edudel.smcapp.ui.theme.SMCAppTheme

var OTP:String? = null

class LoginScreen : ComponentActivity() {
    private val vm: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SMCAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginForm()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
    @Composable
    fun LoginForm() {

        val uiState by vm.uiState.collectAsState()

        val context = LocalContext.current
        val loginId by vm.loginId.collectAsState()
        val loginType by vm.loginType.collectAsState()

        var isExpanded by remember { mutableStateOf(false) }
        var loginIdLabel by remember { mutableStateOf("") }
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text("Login to the SMC App",
                modifier = Modifier.padding(vertical = 16.dp),
                fontSize = 24.sp)


            ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded=it} ) {
                OutlinedTextField(loginType.displayValue, {}, readOnly = true,
                    trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)},
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier
                        .menuAnchor()
                        .focusProperties {
                            canFocus = false
                            enter = { FocusRequester.Cancel }
                        }

                )
                ExposedDropdownMenu(expanded = isExpanded, onDismissRequest = {isExpanded=false}) {
                    LoginType.entries.forEach {
                        DropdownMenuItem( { Text(it.displayValue) },
                            {
                                vm.loginType.value = it
                                loginIdLabel = "Enter " + when(it){
                                    LoginType.HOS, LoginType.Teacher -> "Employee ID"
                                    LoginType.Zone, LoginType.District, LoginType.Hq, LoginType.Admin ->"Login ID"
                                    else ->"Mobile Number"
                                }
                                isExpanded = false
                            })
                    }
                }
            }
            OutlinedTextField(value = loginId, onValueChange = { vm.loginId.value = it },
                Modifier.padding(bottom = 12.dp), label = { Text(loginIdLabel) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Button(onClick = {
                vm.handleLogin(loginId, loginType)
            }) {
                Text(text = "Send OTP")
            }
        }

        val showOtpDialog by vm.showOtpDialog.collectAsState()
        if(showOtpDialog){
            ModalBottomSheet(onDismissRequest = { vm.showOtpDialog.value = false }) {
                Column( Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Enter the otp sent to your mobile number XXX720")

                    var enteredOtp by remember { mutableStateOf("") }
                    OutlinedTextField(value = enteredOtp, onValueChange = {enteredOtp=it},
                        Modifier.padding(bottom = 12.dp),
                        label = { Text("")},
                        placeholder = {Text("Enter OTP")},
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) )
                    Button(onClick = {
                        if(enteredOtp==OTP){
                            context.startActivity(Intent(context, HomeActivity::class.java))
                            vm.showOtpDialog.value = false
                            finish()
                        } else {
                            Toast.makeText(context, "Wrong otp", Toast.LENGTH_SHORT).show()
                        }
                    }) {
                        Text(text = "Login")
                    }
                }
            }
        }
//        if(uiState !is UIState.Success){
//            AlertDialog({}) {
//                when(uiState){
//                    is UIState.Error -> Icon(Icons.Default.Info, "")
//                    is UIState.Loading -> CircularProgressIndicator()
//                    else -> {}
//                }
//                Text(uiState.message!!)
//            }
//        }


        when(uiState){
            is UIState.Loading -> AlertDialog({}) {
                CircularProgressIndicator()
                Text(uiState.message!!)
            }
            is UIState.Error -> {
                AlertDialog({}) {
                    Icon(Icons.Default.Info, "")
                    Text(uiState.message!!)
                }
            }
            is UIState.Success ->{}
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun Preview() {
        SMCAppTheme {
//        Text("Some test")
            LoginForm()
        }
    }

}