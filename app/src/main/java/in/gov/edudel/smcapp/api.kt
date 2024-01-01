package `in`.gov.edudel.smcapp

import `in`.gov.edudel.smcapp.models.Meeting
import `in`.gov.edudel.smcapp.models.Member
import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.LocalTime

object api {

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
}