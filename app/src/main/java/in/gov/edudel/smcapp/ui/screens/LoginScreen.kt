package `in`.gov.edudel.smcapp.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.gov.edudel.smcapp.ui.theme.SMCAppTheme

class LoginScreen : ComponentActivity() {
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
}

@Composable
fun LoginForm(){
    val context = LocalContext.current
    Column(Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

//        OutlinedTextField(value = "", onValueChange = {}, Modifier.padding(bottom = 12.dp))
//        OutlinedTextField(value = "", onValueChange = {}, Modifier.padding(bottom = 12.dp),
//            label = {Text("Employee ID")})
//        Button(onClick = {
//            context.startActivity(Intent(context, HomeActivity::class.java))
//        }) {
//            Text(text = "Login")
//        }
    }

//    var showOtpDialog by remember{ mutableStateOf(true) }
//    ModalBottomSheet(onDismissRequest = { showOtpDialog = false }) {
//        Column{
//            Text("Enter the otp sent to ypur mobile number XXX720")
//            OutlinedTextField(value = "", onValueChange = {}, Modifier.padding(bottom = 12.dp),
//                label = {Text("Employee ID")})
//            Button(onClick = {}) {
//                Text(text = "Login")
//            }
//        }
//    }

    Column(horizontalAlignment = Alignment.CenterHorizontally){
        Text("Enter the otp sent to ypur mobile number XXX720")
        OutlinedTextField(value = "", onValueChange = {}, Modifier.padding(vertical = 12.dp),
            label = {Text("Enter thr OTP")})
        Button(onClick = {}) {
            Text(text = "Verify OTP")
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Alernate(){

}