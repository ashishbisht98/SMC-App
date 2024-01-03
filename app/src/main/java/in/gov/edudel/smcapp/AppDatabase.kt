package `in`.gov.edudel.smcapp

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import `in`.gov.edudel.smcapp.models.Meeting
import `in`.gov.edudel.smcapp.models.MeetingDao
import `in`.gov.edudel.smcapp.models.User
import `in`.gov.edudel.smcapp.models.UserDao
import java.time.LocalDate
import java.time.LocalTime

@Database(entities = [User::class, Meeting::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun meetingDao(): MeetingDao
}


class Converters{
    // todo: implement these
    @TypeConverter
    fun dateToString(date: LocalDate?): String{
        return ""
    }

    @TypeConverter
    fun stringToDate(date: String?): LocalDate{
        return LocalDate.now()
    }

    @TypeConverter
    fun timeToString(date: LocalTime?): String{
        return ""
    }

    @TypeConverter
    fun stringToTime(date: String?): LocalTime{
        return LocalTime.now()
    }

}