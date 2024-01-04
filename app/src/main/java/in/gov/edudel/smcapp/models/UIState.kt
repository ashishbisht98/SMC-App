package `in`.gov.edudel.smcapp.models

sealed class UIState(val message: String = ""){
    class Loading(message: String = "Loading"): UIState(message)
    class Error(message: String): UIState(message)
    data object Success : UIState()

}