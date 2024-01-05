package `in`.gov.edudel.smcapp.ui.screens

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.MenuItemColors
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import `in`.gov.edudel.smcapp.ui.screens.ui.theme.roboto
import `in`.gov.edudel.smcapp.ui.theme.SMCAppTheme
import kotlinx.coroutines.flow.MutableStateFlow

var OTP = ""

class LoginScreen : ComponentActivity() {
    private val vm: LVM by viewModels()
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
        val context = LocalContext.current
        val loginId by vm.loginId.collectAsState()
        val loginType by vm.loginType.collectAsState()

        var isExpanded by remember { mutableStateOf(false) }
        var loginIdLabel by remember { mutableStateOf("") }

        Card(
            modifier = Modifier
                .padding(5.dp)
                .wrapContentSize(),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFFf2f6fc),
            )
        ) {
            Column(
                Modifier.padding(5.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    "Login to the SMC App",
                    modifier = Modifier.padding(10.dp),
                    fontFamily = roboto,
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp,
                )

                val types = listOf("Teacher Member", "Parent Member", "Social Worker", "HOS")

                ExposedDropdownMenuBox(
                    expanded = isExpanded,
                    onExpandedChange = { isExpanded = !isExpanded }) {
                    OutlinedTextField(loginType, {}, readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded) },
                       // colors = ExposedDropdownMenuDefaults.textFieldColors(),
                        modifier = Modifier
                            .menuAnchor()
                            .padding(10.dp)
                            .focusProperties {
                                canFocus = false
                                enter = { FocusRequester.Cancel }
                            }

                    )

                    ExposedDropdownMenu(
                        expanded = isExpanded,
                        onDismissRequest = { isExpanded = false },Modifier.background(color = Color.White).padding()) {
                        types.forEach {
                            DropdownMenuItem({ Text(it, fontFamily = roboto, fontWeight = FontWeight.Black, fontSize = 15.sp) },

                                {
                                    vm.loginType.value = it
                                    loginIdLabel = when (it) {
                                        "Teacher Member", "HOS" -> "Enter Employee ID"
                                        else -> "Enter Mobile Number"
                                    }
                                    isExpanded = false
                                })
                        }
                    }
                }
                OutlinedTextField(
                    value = loginId, onValueChange = { vm.loginId.value = it },
                    Modifier.padding(10.dp), label = { Text(loginIdLabel, fontFamily = roboto) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Button(onClick = {
                    sendOtp()
                    vm.showOtpDialog.value = true
                }) {
                    Text(text = "Send OTP", fontFamily = roboto)
                }
            }
        }

        val showOtpDialog by vm.showOtpDialog.collectAsState()
        if (showOtpDialog) {
            ModalBottomSheet(onDismissRequest = { vm.showOtpDialog.value = false }) {
                Column(
                    Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Enter the otp sent to your mobile number XXX720", fontFamily = roboto)

                    var enteredOtp by remember { mutableStateOf("") }
                    OutlinedTextField(
                        value = enteredOtp, onValueChange = { enteredOtp = it },
                        Modifier.padding(bottom = 5.dp),
                        label = { Text("") },
                        placeholder = { Text("Enter OTP", fontFamily = roboto) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                    Button(
                        onClick = {
                            if (verifyOtp()) {
                                finish()
                                vm.showOtpDialog.value = false
                                context.startActivity(Intent(context, HomeActivity::class.java))
                            } else {
                                Toast.makeText(context, "Wrong otp", Toast.LENGTH_SHORT).show()
                            }
                        },
                        Modifier.padding(bottom = 80.dp)
                    ) {
                        Text(text = "Submit", fontFamily = roboto)
                    }
                }
            }
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

fun sendOtp() {
    OTP = "1234"
}

fun verifyOtp() = OTP == "1234"

class LVM : ViewModel() {
    val loginType = MutableStateFlow("")
    val loginId = MutableStateFlow("")
    val showOtpDialog = MutableStateFlow(false)

}