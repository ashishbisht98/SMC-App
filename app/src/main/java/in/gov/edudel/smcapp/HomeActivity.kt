package `in`.gov.edudel.smcapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.gov.edudel.smcapp.ui.theme.SMCAppTheme

sealed class TabItem(val title: String, val icon: Int) {
    object Meeting : TabItem("Meeting", R.drawable.outline_assignment_24)
    object Members : TabItem("Members", R.drawable.baseline_person_24)
    companion object {
        fun values()= listOf(Meeting,Members,)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SMCAppTheme{
            // A surface container using the 'background' color from the theme
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Tabs()
            }
        }
        }
    }
}

@Composable
fun Tabs() {
    var selectedTab:TabItem by remember { mutableStateOf(TabItem.Meeting) }

    Column {
        TabRow(
            selectedTabIndex = TabItem.values().indexOf(selectedTab),
            Modifier.background(MaterialTheme.colorScheme.primary),
            contentColor = Color.Black
        ) {
            TabItem.values().forEachIndexed { _, tab ->
                Tab(
                    selected = selectedTab == tab,
                    onClick = { selectedTab = tab },
                    icon = { Image(painterResource(tab.icon),"content description") },
                    text = { Text(tab.title) }
                )
            }
        }

        when (selectedTab) {
            TabItem.Meeting -> MeetingTab()
            TabItem.Members -> MembersTab()
        }
    }
}

@Composable
fun TabContent(content: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = content)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SMCAppTheme {
        Tabs()
    }
}
