package `in`.gov.edudel.smcapp.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import `in`.gov.edudel.smcapp.ui.theme.SMCAppTheme

class AddMemberScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SMCAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AddMemberForm()
                }
            }
        }
    }
}

@Composable
fun AddMemberForm(){
    Text("Add member form")
}

@Preview(showBackground = true)
@Composable
fun AddMemberFormPreview() {
    SMCAppTheme {
        AddMemberForm()
    }
}