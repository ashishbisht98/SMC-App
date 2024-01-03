package `in`.gov.edudel.smcapp.models

import androidx.room.Entity

@Entity
data class Member(
    val id: Int,
    val name: String,
    val memberType: String,
    val gender: String,
    val mobile: String
)



