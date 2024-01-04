package `in`.gov.edudel.smcapp.ui.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.*

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import `in`.gov.edudel.smcapp.R
import `in`.gov.edudel.smcapp.models.Meeting
import `in`.gov.edudel.smcapp.ui.theme.SMCAppTheme
import java.time.LocalDate
import java.time.LocalTime

private val meetings = listOf(
    Meeting(1,"Meeting Title 1", "Agenda for Meeting 1", LocalDate.now(), LocalTime.now(), "122122"),
    Meeting(2,"Meeting Title 2", "Agenda for Meeting 2", LocalDate.now(), LocalTime.now(), "122122"),
    Meeting(3,"Meeting Title 3", "Agenda for Meeting 3", LocalDate.now(), LocalTime.now(), "122122"),
    Meeting(4,"Meeting Title 4", "Agenda for Meeting 4", LocalDate.now(), LocalTime.now(), "122122"),
    Meeting(5,"Meeting Title 5", "Agenda for Meeting 5", LocalDate.now(), LocalTime.now(), "122122"),
    Meeting(1,"Meeting Title 1", "Agenda for Meeting 1", LocalDate.now(), LocalTime.now(), "122122"),
    Meeting(2,"Meeting Title 2", "Agenda for Meeting 2", LocalDate.now(), LocalTime.now(), "122122"),
    Meeting(3,"Meeting Title 3", "Agenda for Meeting 3", LocalDate.now(), LocalTime.now(), "122122"),
    Meeting(4,"Meeting Title 4", "Agenda for Meeting 4", LocalDate.now(), LocalTime.now(), "122122"),
    Meeting(5,"Meeting Title 5", "Agenda for Meeting 5", LocalDate.now(), LocalTime.now(), "122122"),
)

val tabs = listOf(
    TabItem("Home", Icons.Outlined.Home, Icons.Filled.Home ){ MeetingList(list = meetings) },
    TabItem("Members", Icons.Outlined.AccountCircle, Icons.Filled.AccountCircle ){ MembersTab() }
)

class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            SMCAppTheme{
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Tabs(tabs)
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SMCAppTheme {
        Tabs(tabs)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Tabs(tabs: List<TabItem>){
    var selectedTab by remember { mutableIntStateOf(0) }
    val pagerState = rememberPagerState { tabs.size }
    LaunchedEffect(selectedTab){
            pagerState.animateScrollToPage(selectedTab)
    }
    LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
        if(!pagerState.isScrollInProgress)
            selectedTab = pagerState.currentPage
    }

    Column {
        TabRow(selectedTabIndex = selectedTab) {
            tabs.forEachIndexed { myIndex, tab ->
                Tab(
                    selected = selectedTab == myIndex,
                    onClick = {selectedTab = myIndex},
                    icon = { Icon(if(selectedTab == myIndex)tab.activeIcon else tab.icon, tab.title) },
                    text = {Text(tab.title)}
                )
            }
        }
        HorizontalPager(state = pagerState, verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
             tabs[it].content()
        }
    }
}

data class TabItem(val title:String, val icon: ImageVector, val activeIcon: ImageVector, val content: @Composable ()->Unit)
