package `in`.gov.edudel.smcapp

import android.content.Context
import `in`.gov.edudel.smcapp.models.User
import `in`.gov.edudel.smcapp.models.User1

object UserService {

    fun Context.getSavedAppUser(): User?{
        val prefs = getSharedPreferences("_", 0)
        val name = prefs.getString("user.name", "")
        val id = prefs.getInt("user.id", 0)
        val type = prefs.getString("user.type", "UNKNOWN")
        val designation = prefs.getString("user.designation", "")
        val mobile = prefs.getString("user.mobile", "")
        val schoolID = prefs.getString("user.schoolId", "")
        val zoneID = prefs.getString("user.zoneId", "")
        val districtID = prefs.getString("user.districtId", "")
        return if(id==0) null else User(id, name!!, type!!, designation!!, mobile!!, schoolID, zoneID, districtID)
    }

//    fun Context.saveAppUser(user: User){
//        getSharedPreferences("_", 0).edit(true){
//            putString("user.name", user.name)
//            putString("user.id", user.id)
//            putString("user.type", user.loginType)
//            putString("user.designation", user.designation)
//            putString("user.mobile", user.mobile)
//            putString("user.schoolId", user.schoolId)
//            putString("user.zoneId", user.zoneId)
//            putString("user.districtId", user.districtId)
//        }
//    }

}