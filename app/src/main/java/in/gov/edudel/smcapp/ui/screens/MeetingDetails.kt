package `in`.gov.edudel.smcapp.ui.screens

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.gov.edudel.smcapp.api
import `in`.gov.edudel.smcapp.models.Meeting
import `in`.gov.edudel.smcapp.models.Member
import `in`.gov.edudel.smcapp.ui.theme.SMCAppTheme
import java.time.LocalDate
import java.time.LocalTime

class MeetingDetails : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val meetingId = intent.getStringExtra("meeting.id")
        if(meetingId == null){
            Toast.makeText(this, "No meeting to show", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setContent {
            var meeting by remember { mutableStateOf<Meeting?>(null) }
            LaunchedEffect(Unit){
                meeting = api.getMeetingDetails(meetingId.toInt())
                if(meeting == null){
                    Toast.makeText(this@MeetingDetails, "No such meeting found", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            SMCAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Scaffold(topBar = {
                        TopAppBar(
                            title = { Text(text = "Meeting Details") },
                            navigationIcon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back",
                                    modifier = Modifier.padding(end = 15.dp)
                                        .clickable { onNavigateUp() }
                                )
                            })
                    },
                        content = {
                            MeetingDetailsUI(meeting, it)
                        })
                }
            }
        }
    }
}


@Composable
fun MeetingDetailsUI(meeting: Meeting?, padding: PaddingValues) {
    if( meeting == null){
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            CircularProgressIndicator()
        }
        return
    }
    Column(
        Modifier
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
            .padding(padding).padding(12.dp),

        ) {

        Text("Basic Info", Modifier.offset(8.dp), fontSize = 16.sp)
        Card(
            Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
        ) {
            Column(Modifier.padding(10.dp)) {
                Row {
                    Text("Title: ")
                    Text(meeting.title!!, fontWeight = FontWeight.Bold)
                }
                Row {
                    Text("Agenda: ")
                    Text(meeting.agenda!!, fontWeight = FontWeight.Bold)
                }
                Row {
                    Text("Date: ")
                    Text(meeting.date.toString(), fontWeight = FontWeight.Bold)
                }
                Row {
                    Text("Time: ")
                    Text(meeting.time.toString(), fontWeight = FontWeight.Bold)
                }
            }
        }


        Text(
            "Minutes of the meeting",
            Modifier
                .offset(8.dp)
                .padding(top = 20.dp), fontSize = 16.sp
        )
        Card(
            Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
        ) {
            Column(Modifier.padding(10.dp)) {
                Row(Modifier.padding(8.dp).fillMaxWidth(), Arrangement.End) {
                    Icon(Icons.Default.Info, contentDescription = "download icon", Modifier.padding(end=8.dp))
                    Text("Download Link", fontWeight = FontWeight.Bold)
                }

                Text("Brief: ", fontWeight = FontWeight.Bold)

                Row {
                    Text("""
                        Lorem ipsum dolor sit amet consectetur, adipisicing elit. Libero cumque vero doloribus nulla ratione? Ducimus voluptatum eos voluptas et incidunt at sed possimus quia nihil, laborum, quos voluptates consequuntur consequatur.
                        Laudantium autem deserunt incidunt ullam iste, libero mollitia modi alias officiis temporibus corporis quod doloremque ex consectetur aut sit, itaque amet odio. Alias, fugiat. Aliquid iure nulla molestiae voluptas vel.
                        Ipsum sed inventore commodi, ut quaerat recusandae cumque deserunt incidunt beatae unde qui enim dolore, nesciunt ipsam corrupti eaque, quos sapiente at cum libero optio. Hic amet impedit deserunt facere.
                        Reprehenderit nihil enim, fuga, beatae ab eius hic alias impedit nesciunt vel nemo deserunt incidunt consectetur quaerat placeat earum. Doloribus tenetur cupiditate illum excepturi quae suscipit magni dicta adipisci modi.
                    """.trimIndent())

                }


            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMeetingDetails() {
    val meeting = Meeting(1, "title", "agenda", LocalDate.now(), LocalTime.NOON, "2128008")
    SMCAppTheme{ MeetingDetailsUI(meeting, PaddingValues(20.dp)) }

//    Column(modifier = Modifier.fillMaxSize(),
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
//        CircularProgressIndicator()
//    }

}