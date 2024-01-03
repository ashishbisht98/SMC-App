package `in`.gov.edudel.smcapp.models

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Entity
data class Meeting(
    @PrimaryKey
    val id: Int?,
    val title: String?,
    val agenda: String?,
    val date: LocalDate?,
    val time: LocalTime?,
    val schoolId: String?
)


@Dao
interface MeetingDao {
    @Query("SELECT * FROM meeting")
    suspend fun getAll(): List<Meeting>

    @Query("SELECT * FROM meeting WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<Meeting>

    @Insert
    fun insertAll(vararg users: Meeting)

    @Delete
    fun delete(user: Meeting)
}