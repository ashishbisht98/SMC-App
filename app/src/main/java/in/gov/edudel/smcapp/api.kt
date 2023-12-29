package `in`.gov.edudel.smcapp

import `in`.gov.edudel.smcapp.models.Meeting
import `in`.gov.edudel.smcapp.models.Member
import `in`.gov.edudel.smcapp.ui.screens.TabItem
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalTime

object api {
    suspend fun getMember(memberId: Int): Member? {
        delay(1000)
        return Member(1, "amit", "Teacher", "980989889")
    }


    suspend fun getMeetingDetails(meetingId: Int): Meeting?{
        delay(1000)
        return Meeting(1, "title", "agenda", LocalDate.now(), LocalTime.NOON, "2128008")

    }
}