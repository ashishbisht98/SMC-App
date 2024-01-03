package `in`.gov.edudel.smcapp.models

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query

class User1(
    var id: String,
    var name: String,

    var type: String,
    var designation: String,
    var mobile: String,
    var schoolID: String?,
    var zoneID: String?,
    var districtID: String?
)

@Entity
data class User(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "designation") val designation: String,
    @ColumnInfo(name = "mobile") val mobile: String,
    @ColumnInfo(name = "schoolId") val schoolID: String?,
    @ColumnInfo(name = "zoneId") val zoneID: String?,
    @ColumnInfo(name = "districtId") val districtID: String?

)

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    suspend fun getAll(): List<User>

    @Query("SELECT * FROM user WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<User>

    @Query("SELECT * FROM user WHERE name LIKE :name LIMIT 1")
    fun findByName(name: String): User

    @Insert
    fun insertAll(vararg users: User)

    @Delete
    fun delete(user: User)

}