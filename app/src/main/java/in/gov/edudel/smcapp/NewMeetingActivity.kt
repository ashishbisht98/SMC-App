package `in`.gov.edudel.smcapp

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.ViewModel
import com.google.type.Date
import `in`.gov.edudel.smcapp.ui.theme.SMCAppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.Calendar
import java.util.Locale


class NewMeetingActivity: ComponentActivity() {
    private val vm by viewModels<NewMeetingViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        val viewModel =
            super.onCreate(savedInstanceState)
        setContent {
            SMCAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    //modifier = Modifier(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NewMeeting()
                }
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun NewMeeting() {

        val date by vm.date.collectAsState()
        val showdatepicker by vm.showdatepicker.collectAsState()
        ElevatedCard(
            Modifier
                .fillMaxSize()
                .padding(20.dp).shadow(8.dp),) {


            Spacer(modifier = Modifier
                .height(10.dp))
            Text(text = "Create a Meeting", Modifier.align(Alignment.CenterHorizontally))
            Spacer(modifier = Modifier
                .height(20.dp))
            TextField(date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
                {},
                Modifier.clickable {
                    vm.showdatepicker.value = true
                },
                enabled = false,
                readOnly = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        if (showdatepicker) {
            val datePickerState = rememberDatePickerState(null, null, 2019..2024)
            DatePickerDialog(onDismissRequest = { vm.showdatepicker.value = false },
                dismissButton = { Text(text = "Cancel", modifier = Modifier.padding(10.dp)) },
                confirmButton = {
                    Text(text = "OK", Modifier.clickable {
                        vm.showdatepicker.value = false

                        vm.date.value = Instant.ofEpochMilli(datePickerState.selectedDateMillis!!)
                            .atZone(ZoneId.systemDefault()).toLocalDate()

                    })
                }) {
                DatePicker(state = datePickerState,)
            }
        }
    }

    @Preview
    @Composable
    fun Prev() {
        NewMeeting()
    }
}
class NewMeetingViewModel() :ViewModel() {
    val showdatepicker = MutableStateFlow(false)
    var date = MutableStateFlow(LocalDate.now())

}

//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MaterialCard(title:String) {
//    val context = LocalContext.current
//    ElevatedCard(modifier = Modifier
//        .fillMaxWidth()
//        .padding(5.dp)
//        .shadow(8.dp),
//
////        onClick = {
////            context.startActivity(Intent(context, ViewIssue::class.java ).apply {
////                putExtra("issue", issue)
////            })
////        }
//    ) {
//        Row(Modifier.padding(10.dp)) {
//            Spacer(modifier = Modifier.width(26.dp))
//            Text(
//                text = title,
//                Modifier
//                    .align(Alignment.CenterVertically)
//                    .weight(2F),
//                fontWeight = FontWeight.Bold,
//                fontSize = 15.sp
//            )
//        }
//    }
//}


