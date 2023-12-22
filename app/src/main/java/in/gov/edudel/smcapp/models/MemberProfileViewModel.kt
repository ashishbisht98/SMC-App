package `in`.gov.edudel.smcapp.models

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MemberProfileViewModel: ViewModel() {
    var member = MutableStateFlow<Member?>(null)
}