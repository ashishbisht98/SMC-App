package `in`.gov.edudel.smcapp

import android.content.Context
import androidx.compose.ui.platform.LocalContext
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import `in`.gov.edudel.smcapp.utils.toast
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import kotlin.random.Random
import kotlin.random.nextInt

object SMSService{
    private const val username = "edudel-Am"
    private val password = md5("edualumni1!")
    private const val secureKey = "096a9d5c-a414-4107-9332-d6d73a2d7dcc"
    private const val templateId = "1007059202141973277"
    private const val senderId = "EDUDEL"

    val baseParams = mutableMapOf(
        "senderid" to  senderId,
        "smsservicetype" to  "otpmsg",
        "username" to  username,
        "password" to  password,
        "templateid" to  templateId )

    fun sendOTP(mobile: String):String? {
        val otp = Random(System.currentTimeMillis()).nextInt(100000..999999).toString()
        val message = "OTP for login with SMC App is $otp (valid for 5 mins). " +
                "Do not share this OTP to anyone for security reasons.\n\n-EDUDEL"

        try{
            val hash = hashGenerator(username, senderId, message, secureKey)

            SmcApp.requestQueue.add(object : StringRequest(
                Method.POST, "https://msdgweb.mgov.gov.in/esms/sendsmsrequestDLT",
                { },
                { }) {
                override fun getParams() = baseParams + mutableMapOf(
                    "mobileno" to mobile,
                    "content" to message,
                    "key" to hash
                )
            })
        } catch(e:Exception){
            return null
        }
        return otp
    }

    private fun hashGenerator1(userName: String, senderId: String, content: String, secureKey: String): String {
        val inputString = "${userName.trim()})${senderId.trim()}${content.trim()}${secureKey.trim()}"
        var outputString = ""

        try {
            val bytes = MessageDigest.getInstance("SHA-512").apply {
                update(inputString.toByteArray())
            }.digest()

            outputString = bytes.joinToString(""){ byte -> "%02x".format(byte) }

        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        return outputString
    }

    private fun hashGenerator(userName: String, senderId: String, content: String, secureKey: String): String? {
        val finalString = StringBuffer()
        finalString.append(userName.trim { it <= ' ' }).append(senderId.trim { it <= ' ' })
            .append(content.trim { it <= ' ' }).append(secureKey.trim { it <= ' ' })
        val hashGen = finalString.toString()
        var outputString:String? = null

        val byteData = MessageDigest.getInstance("SHA-512").apply {
            update(hashGen.toByteArray())
        }.digest()
        outputString = byteData.joinToString(""){ byte -> "%02x".format(byte) }

        return outputString
    }

    private fun md5(text: String): String {
        var md5 = MessageDigest.getInstance("SHA-1").apply {
            update(text.toByteArray(charset("iso-8859-1")))
        }.digest()
        return md5.joinToString(""){ byte -> "%02x".format(byte) }
    }
}