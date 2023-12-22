package `in`.gov.edudel.smcapp.models

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Meeting(val id: Int, val title: String, val agenda: String, val date: LocalDate,
                   val time: LocalTime, val schoolId: String )