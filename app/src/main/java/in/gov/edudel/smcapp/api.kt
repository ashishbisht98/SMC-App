package `in`.gov.edudel.smcapp

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import `in`.gov.edudel.smcapp.models.Meeting
import `in`.gov.edudel.smcapp.models.Member
import kotlinx.coroutines.delay
import kotlinx.coroutines.suspendCancellableCoroutine
import org.json.JSONException
import org.json.JSONObject
import java.time.LocalDate
import java.time.LocalTime
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

object api {

    const val employeeDataUrl = "https://www.edudel.nic.in/libraryservice/LibraryAuth.asmx/AuthUserJSON"
    suspend fun getMembers(schoolId: String): List<Member>? {
        delay(1000)

        return listOf(
            Member(1, "Amit", "Teacher", "Male", "980989889"),
            Member(1, "Ashish", "Teacher", "Male", "980989889"),
            Member(1, "Pankaj", "Teacher", "Male", "980989889"),
            Member(1, "Anurag", "Teacher", "Male", "980989889"),
            Member(1, "Pankaj", "Teacher", "Male", "980989889"),
            Member(1, "Amit", "Teacher", "Male", "980989889"),
            Member(1, "Ashish", "Teacher", "Male", "980989889"),
            Member(1, "Pankaj", "Teacher", "Male", "980989889"),
            Member(1, "Anurag", "Teacher", "Male", "980989889"),
            Member(1, "Pankaj", "Teacher", "Male", "980989889"),
        )
    }

    suspend fun getMemberDetails(memberId: Int): Member? {
        delay(1000)
        return Member(1, "amit", "Teacher", "Male","980989889")
    }


    suspend fun getMeetings(schoolId: String): List<Meeting>?{
        delay(1000)
        return listOf(
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

    }

    suspend fun getMeetingDetails(meetingId: Int): Meeting?{
        delay(1000)
        return Meeting(1, "title", "agenda", LocalDate.now(), LocalTime.NOON, "2128008")

    }

    fun sendOtp(mobile: String) = if(mobile=="1234") "1234" else null //SMSService.sendOTP(mobile)


    suspend fun fetchData(url: String, params: Map<String, String>): String = suspendCancellableCoroutine {flow->
        SmcApp.requestQueue.add(object: StringRequest(Request.Method.GET, url, {
            flow.resume(it)
        }, {
            flow.resumeWithException(it)
        }){
            override fun getParams() = params
        })

    }



//    fun getEmployeeDetails(empId: String, context: Context){
//        val encryptedId = utils.encrypt(empId)
//        val request: StringRequest = object : StringRequest(Method.POST, employeeDataUrl,
//            { response ->
//                try {
//
//                    var test = response.replace( "<?xml version=\"1.0\" encoding=\"utf-8\"?>", "" )
//                        .replace("<string xmlns=\"http://tempuri.org/\">", "")
//                        .replace("</string>", "")
//
//                    val respObj = JSONObject(test)
//                    val name = respObj.getString("EmployeeName")
//                    val id = respObj.getString("EmployeeID")
//                    val designation = respObj.getString("EmployeeDesignation")
//                    val mobile = respObj.getString("EmployeeMobileNo")
//                    val schoolId = respObj.getString("SchoolID")
//                    val zoneId = respObj.getString("ZoneID")
//                    val districtId = respObj.getString("DistrictID")
//
//                    handleLoginData( LoginUser(name, id, userType, designation, mobile, schoolId, zoneId, districtId) )
//                } catch (e: JSONException) {
//                    toast("Not a valid Employee ID")
//                    e.printStackTrace()
//                    loginButton.isEnabled = true
//                    loading.visibility = View.GONE
//                    loginButton.text = "Login using OTP"
//                }
//            },
//            {
//                loginButton.isEnabled = true
//                loading.visibility = View.GONE
//                loginButton.text = "Login using OTP"
//                toast("Failed to process the request")
//                Log.i("abc", "trace is: ${it.stackTraceToString()}")
//            }
//        ) {
//            override fun getParams() = mapOf(
//                "empID" to encryptedId,
//                "password" to Utils.API_ENCRYPTED_PASSWORD
//            )
//        }
//        request.retryPolicy = DefaultRetryPolicy(5000, 2, 2f)
//        Volley.newRequestQueue(this@LoginActivity).add(request)
//    }

//        Volley.newRequestQueue(context).add(object : StringRequest(Method.POST, "",{},{}))


}