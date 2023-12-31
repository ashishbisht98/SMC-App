package `in`.gov.edudel.smcapp.ui.screens

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.gov.edudel.smcapp.models.Meeting
import `in`.gov.edudel.smcapp.ui.screens.ui.theme.SMCAppTheme
import `in`.gov.edudel.smcapp.ui.screens.ui.theme.roboto
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class PreviousMeetings : ComponentActivity() {
    private val meetings = listOf(
        Meeting(
            1,
            "Meeting Title 1",
            "Agenda for Meeting 1",
            LocalDate.now(),
            LocalTime.now(),
            "122122"
        ),
        Meeting(
            2,
            "Meeting Title 2",
            "Agenda for Meeting 2",
            LocalDate.now(),
            LocalTime.now(),
            "122122"
        ),
        Meeting(
            3,
            "Meeting Title 3",
            "Agenda for Meeting 3",
            LocalDate.now(),
            LocalTime.now(),
            "122122"
        ),
        Meeting(
            4,
            "Meeting Title 4",
            "Agenda for Meeting 4",
            LocalDate.now(),
            LocalTime.now(),
            "122122"
        ),
        Meeting(
            5,
            "Meeting Title 5",
            "Agenda for Meeting 5",
            LocalDate.now(),
            LocalTime.now(),
            "122122"
        ),
        Meeting(
            1,
            "Meeting Title 1",
            "Agenda for Meeting 1",
            LocalDate.now(),
            LocalTime.now(),
            "122122"
        ),
        Meeting(
            2,
            "Meeting Title 2",
            "Agenda for Meeting 2",
            LocalDate.now(),
            LocalTime.now(),
            "122122"
        ),
        Meeting(
            3,
            "Meeting Title 3",
            "Agenda for Meeting 3",
            LocalDate.now(),
            LocalTime.now(),
            "122122"
        ),
        Meeting(
            4,
            "Meeting Title 4",
            "Agenda for Meeting 4",
            LocalDate.now(),
            LocalTime.now(),
            "122122"
        ),
        Meeting(
            5,
            "Meeting Title 5",
            "Agenda for Meeting 5",
            LocalDate.now(),
            LocalTime.now(),
            "122122"
        ),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SMCAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TheList(meetings)
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview2() {
        SMCAppTheme {
            TheList(meetings = meetings)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TheList(meetings: List<Meeting>) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        Icons.Default.ArrowBack, contentDescription = "back",
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                },
                title = { Text("Upcoming Meetings") },
            )
        }
    ) {
        LazyColumn(contentPadding = it) {
            items(meetings) { meeting ->
                MeetingCard(meeting = meeting)
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeetingCard(meeting: Meeting) {
    val context = LocalContext.current
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .wrapContentHeight(),
        colors = CardDefaults.cardColors(containerColor = Color(0xffeaf1fb)),
        onClick = {
            context.startActivity(Intent(context, MeetingDetails::class.java).apply {
                putExtra("meeting.id", meeting.id.toString())
            })
        }) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = meeting.title, fontSize = 20.sp, fontFamily = roboto, fontWeight = FontWeight.W500)
            Row {
                Text(text = meeting.agenda, fontFamily = roboto, fontSize = 15.sp)
                Spacer(modifier = Modifier.weight(1F))
            }
            Spacer(modifier = Modifier.padding(5.dp))
            Row {
                Spacer(modifier = Modifier.weight(1F))
//                Text(
//                    text = meeting.date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
//                    fontSize = 10.sp,
//                    fontFamily = roboto
//                )
//                Text(text = ",", fontSize = 10.sp, fontFamily = roboto)
                Text(
                    text = meeting.time.format(DateTimeFormatter.ofPattern("hh:mm a", Locale.US)),
                    fontSize = 10.sp,
                    fontFamily = roboto,
                    fontWeight = FontWeight.W600
                )

            }
        }
    }
}
