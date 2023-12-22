package `in`.gov.edudel.smcapp.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDate
import java.time.LocalTime

class NewMeetingViewModel() : ViewModel() {
    val showDatePicker = MutableStateFlow(false)
    val showTimePicker = MutableStateFlow(false)
    var date = MutableStateFlow(LocalDate.now().plusDays(3))
    var time = MutableStateFlow(LocalTime.NOON)
    var title = MutableStateFlow("")
    var agenda = MutableStateFlow("")
//    var meeting = Meeting(0,title.value, agenda.value, date.value, time.value, "")
}