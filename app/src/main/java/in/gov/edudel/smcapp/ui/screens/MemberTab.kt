package `in`.gov.edudel.smcapp.ui.screens

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import `in`.gov.edudel.smcapp.R
import `in`.gov.edudel.smcapp.models.Member


@Composable
fun MembersTab() {
    val members = listOf(
        Member(1, "Amit", "teacher", "9090909090"),
        Member(2, "Ashish", "teacher", "9090909090"),
        Member(3, "Anurag", "teacher", "9090909090"),
    )
    val context = LocalContext.current
    Column {
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                      context.startActivity(Intent(context, AddMemberScreen::class.java))
            },
            modifier = Modifier.padding(10.dp).height(50.dp).align(Alignment.End),
            colors = ButtonDefaults.buttonColors(contentColor = Color.White ),
            shape = RoundedCornerShape(8.dp)
        )
        {
            Row(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Icon
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(24.dp),

                    )

                // Spacer to add some space between icon and text
                Spacer(modifier = Modifier.width(8.dp))

                // Text
                Text(
                    text = "Add Member",
                    // style = MaterialTheme.typography.button
                )
            }
        }
        LazyColumn(contentPadding = PaddingValues(vertical = 16.dp, horizontal = 8.dp)) {
            items(members) { member ->
                MemberCard(member)
            }
        }

    }
}

@Preview
@Composable
fun PreviewLayoutMem() {
    MembersTab()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemberCard(member: Member) {
    val context = LocalContext.current
    ElevatedCard(modifier = Modifier.fillMaxWidth().padding(5.dp).shadow(8.dp),
        onClick = {
            context.startActivity(Intent(context, MemberProfile::class.java).apply {
                putExtra("member.id", member.id.toString())
            })
        }
    ) {
        Row(Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.user),
                contentDescription = null,
                modifier = Modifier.clip(CircleShape).size(50.dp).weight(1F)
            )
            Spacer(modifier = Modifier.width(26.dp))
            Text(
                text = member.name,
                Modifier.weight(2F),
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.width(26.dp))
            Image(
                painter = painterResource(id = R.drawable.cancel),
                contentDescription = null,
                modifier = Modifier.clip(CircleShape).size(30.dp).weight(1F).clickable { }
            )
        }

    }
}