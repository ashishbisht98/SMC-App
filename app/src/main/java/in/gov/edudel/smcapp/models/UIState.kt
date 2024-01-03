package `in`.gov.edudel.smcapp.models

sealed class UIState(val message: String? = null){
    class Loading(message: String = "Loading"): UIState()
    class Error(message: String): UIState()
    data object Success : UIState()

}