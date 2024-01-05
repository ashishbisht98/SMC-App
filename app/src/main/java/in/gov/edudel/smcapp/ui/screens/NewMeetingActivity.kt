package `in`.gov.edudel.smcapp.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import androidx.core.app.NavUtils
import `in`.gov.edudel.smcapp.models.NewMeetingViewModel
import `in`.gov.edudel.smcapp.ui.theme.SMCAppTheme
import java.time.Instant
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Formatter
import java.util.Locale


class NewMeetingActivity : ComponentActivity() {
    val vm: NewMeetingViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SMCAppTheme {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = { Text("Create new Meeting") },
                                navigationIcon = {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "close",
                                        Modifier.padding(12.dp)
                                    )
                                    IconButton(onClick = {
                                        NavUtils.navigateUpTo(
                                            this,
                                            Intent(this, HomeActivity::class.java)
                                        )
                                    }) {
                                    }
                                }
                            )
                        }
                    ) {
                        Box(modifier = Modifier.padding(it), contentAlignment = Alignment.Center) {
                            NewMeeting()
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun NewMeeting() {

        val date by vm.date.collectAsState()
        val showDatePicker by vm.showDatePicker.collectAsState()
        val time by vm.time.collectAsState()
        val showTimePicker by vm.showTimePicker.collectAsState()
        val title by vm.title.collectAsState()
        val agenda by vm.agenda.collectAsState()
//        val title by vm.title.collectAsState()

        ElevatedCard(
            Modifier
                .wrapContentSize()
                .padding(8.dp)
                .shadow(8.dp),
        ) {
            Column( verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 16.dp)
            ) {

                OutlinedTextField(value = title,
                    onValueChange = { vm.title.value = it },
                    label = { Text(text = "Title") }
                )
                OutlinedTextField(
                    value = agenda,
                    onValueChange = { vm.agenda.value = it },
                    label = { Text(text = "Agenda") },
                    minLines = 4, maxLines = 6
                )
                Spacer(modifier = Modifier.height(20.dp))
//                TextField(
//                    date.format(DateTimeFormatter.ofPattern("dd MMM yyyy")), {},
//                    Modifier.clickable { vm.showDatePicker.value = true },
//                    enabled = false,
//                    readOnly = true,
//                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
//                )

                Column(Modifier.fillMaxWidth().padding(start = 32.dp), horizontalAlignment = Alignment.Start) {

                    Row (verticalAlignment = Alignment.CenterVertically) {

                        Text(text = "Select a Date",Modifier.padding(end = 30.dp))
                        OutlinedButton(onClick = { vm.showDatePicker.value = true }) {
                            Text(date.toString())
                        }
                    }

                    Row (verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Select a Time",Modifier.padding(end = 30.dp))
                        OutlinedButton(onClick = { vm.showTimePicker.value = true }) {
                            Text(time.format(DateTimeFormatter.ofPattern("hh:mm a", Locale.US)))
                        }
                    }

                }

                Button(onClick = { /*TODO*/ },Modifier.padding(10.dp)) {
                    Text("Create")
                }
                if (showDatePicker) {
                    ShowDatePicker()
                }

                if (showTimePicker) {
                    ShowTimePicker()
                }
            }
        }

    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ShowDatePicker() {
        val datePickerState = rememberDatePickerState(null, null, 2022..2024)
        DatePickerDialog(onDismissRequest = { vm.showDatePicker.value = false },
            dismissButton = {
                Button(onClick = { vm.showDatePicker.value = false }) {
                    Text(text = "Cancel")

                }
            },
            confirmButton = {
                Button(onClick = {
                    vm.showDatePicker.value = false
                    vm.date.value = Instant.ofEpochMilli(datePickerState.selectedDateMillis!!)
                        .atZone(ZoneId.systemDefault()).toLocalDate()
                }
                ) {
                    Text(text = "OK")
                }
            }) {
            DatePicker(state = datePickerState)
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ShowTimePicker() {
        val timePickerState = rememberTimePickerState()
        DatePickerDialog(
            onDismissRequest = { vm.showTimePicker.value = false },
            confirmButton = {
                Button(onClick = {
                    vm.showTimePicker.value = false
                    vm.time.value = LocalTime.of(timePickerState.hour, timePickerState.minute)
                }) { Text("OK") }
            })
        {

            TimePicker(state = timePickerState,  Modifier.padding(15.dp).align(Alignment.CenterHorizontally))
        }
    }

    @Preview
    @Composable
    fun Prev() {
        NewMeeting()
    }

}


