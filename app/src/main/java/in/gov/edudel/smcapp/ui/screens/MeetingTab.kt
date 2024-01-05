package `in`.gov.edudel.smcapp.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.gov.edudel.smcapp.R
import `in`.gov.edudel.smcapp.models.Meeting
import `in`.gov.edudel.smcapp.ui.screens.ui.theme.roboto
import java.time.LocalDate
import java.time.LocalTime

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

@Composable
fun MeetingTab() {
    val context = LocalContext.current
    Column {
        Text(
            text = "Meeting Timeline",
            Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(12.dp))
        MemberCard(R.drawable.upcoming_meet, "Upcoming Meetings") {
            context.startActivity(Intent(context, PreviousMeetings::class.java))
        }

        Spacer(modifier = Modifier.height(12.dp))
        MemberCard(R.drawable.prev_meet, "Previous Meetings") {}
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { context.startActivity(Intent(context, NewMeetingActivity::class.java)) },
            Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Create new Meeting")
        }
    }
}

@Preview
@Composable
fun PreviewLayout() {
    MeetingList(meetings)
    //MeetingTab()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberCard(imageId: Int, title: String, action: () -> Unit) {
    val context = LocalContext.current
    ElevatedCard(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)
        .shadow(8.dp),
        onClick = { action() }
    ) {
        Row(Modifier.padding(10.dp)) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)

            )
            Spacer(modifier = Modifier.width(26.dp))
            Text(
                text = title,
                Modifier
                    .align(Alignment.CenterVertically)
                    .weight(2F),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun Meetingitem(m: Meeting) {
    Row(
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier
            .padding(horizontal = 8.dp)
            .wrapContentHeight()
            .fillMaxWidth()
    ) {
        Box(contentAlignment = Alignment.Center) {

            Divider(
                Modifier
                    .width(3.dp)
                    .height(140.dp),
                color = Color(0xFFB4D4FF)
            )
       Card (Modifier.background(color = Color.Transparent), shape = CircleShape, elevation = CardDefaults.cardElevation(10.dp)){
           Column(
               Modifier
                   .background(color = Color(0xFFFFECD6), shape = CircleShape)
                   .size(50.dp)
                   .padding(10.dp),
               verticalArrangement = Arrangement.Top , horizontalAlignment = Alignment.CenterHorizontally
           ) {
               Text(modifier = Modifier
                   .background(
                       color = Color(0xFFFFECD6),
                       shape = CircleShape
                   )
                   .padding(0.dp),
                   color = Color.Black,
                   text = "25",
                   fontWeight = FontWeight.Black,
                   fontFamily = roboto,
                   fontSize = 14.sp
               )
               Text(modifier = Modifier.padding(0.dp),
                   color = Color(0x99000000),
                   text = "Dec",
                   fontFamily = roboto,
                   fontSize = 10.sp, fontWeight = FontWeight.W900
               )
           }
       }
        }
        MeetingCard(m)
    }
}
@Composable
fun MeetingList(list: List<Meeting>) {
    val context = LocalContext.current
    Scaffold(floatingActionButton = {
        ExtendedFloatingActionButton(
            onClick = { context.startActivity(Intent(context, NewMeetingActivity::class.java))},
            icon = { Icon(Icons.Outlined.AddCircle, "Extended floating action button.") },
            text = { Text(text = "Create Meeting", fontFamily = roboto, fontWeight = FontWeight.Bold, fontSize = 15.sp) }, containerColor = Color(0xffc4eed0), shape = CircleShape
            )

    }) { abc ->
        LazyColumn(Modifier.padding(abc)) {
            items(list) {
                Meetingitem(it)
            }
        }
    }

}