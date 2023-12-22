package `in`.gov.edudel.smcapp.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.gov.edudel.smcapp.R

@Composable
fun MeetingTab() {
    val context = LocalContext.current
    Column {
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
    MeetingTab()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberCard(imageId: Int, title: String, action: () -> Unit) {
    val context = LocalContext.current
    ElevatedCard(modifier = Modifier.fillMaxWidth().padding(5.dp).shadow(8.dp),
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