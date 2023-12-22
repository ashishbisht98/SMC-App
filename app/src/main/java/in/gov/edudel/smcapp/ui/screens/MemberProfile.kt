package `in`.gov.edudel.smcapp.ui.screens

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.collectAsState
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
import `in`.gov.edudel.smcapp.models.Member
import `in`.gov.edudel.smcapp.models.MemberProfileViewModel
import `in`.gov.edudel.smcapp.ui.theme.SMCAppTheme

class MemberProfile : ComponentActivity() {

    private val vm: MemberProfileViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val memberId = intent.getStringExtra("member.id")
        if(memberId == null){
            Toast.makeText(this, "No member to show", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        setContent {
            val member by vm.member.collectAsState()
            LaunchedEffect(Unit){
                vm.member.value = api.getMember(memberId.toInt())
                if(member == null){
                    Toast.makeText(this@MemberProfile, "No such member found", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            SMCAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Scaffold( topBar = { TopAppBar(
                        title = { Text(text = "Profile") },
                        navigationIcon = {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                modifier = Modifier
                                    .padding(end = 15.dp)
                                    .clickable { onNavigateUp() }
                            )
                        })
                    },
                    content = {
                        MemberProfileUI(member, it)
                    })
                }
            }
        }
    }
}


@Composable
fun MemberProfileUI(member: Member?, padding: PaddingValues) {
    if( member == null){
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
            .padding(padding)
            .padding(12.dp),

    ) {


        Image(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Profile picture",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .scale(5f)
                .padding(vertical = 35.dp)
        )
        Text("Personal", Modifier.offset(8.dp), fontSize = 16.sp)
        Card(
            Modifier
                .padding(top = 5.dp)
                .fillMaxWidth()
        ) {
            Column(Modifier.padding(10.dp)) {
                Row {
                    Text("Name: ")
                    Text(member.name, fontWeight = FontWeight.Bold)
                }
                Row {
                    Text("Type: ")
                    Text(member.memberType, fontWeight = FontWeight.Bold)
                }
                Row {
                    Text("Mobile: ")
                    Text(member.mobile, fontWeight = FontWeight.Bold)
                }
            }
        }

        Text(
            "Child in School",
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
                Row {
                    Text("Name: ")
                    Text(member.name, fontWeight = FontWeight.Bold)
                }
                Row {
                    Text("Type: ")
                    Text(member.memberType, fontWeight = FontWeight.Bold)
                }
                Row {
                    Text("Mobile: ")
                    Text(member.mobile, fontWeight = FontWeight.Bold)
                }
            }
        }

        Text(
            "Contact",
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
                Row {
                    Text("Name: ")
                    Text(member.name, fontWeight = FontWeight.Bold)
                }
                Row {
                    Text("Type: ")
                    Text(member.memberType, fontWeight = FontWeight.Bold)
                }
                Row {
                    Text("Mobile: ")
                    Text(member.mobile, fontWeight = FontWeight.Bold)
                }
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun PreviewMemberProfile() {
//    val member = Member(1,"Amit Kumar", "Teacher", "9090909090")
//    SMCAppTheme{ MemberProfileUI(member, PaddingValues(20.dp)) }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        CircularProgressIndicator()
    }

}