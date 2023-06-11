package shashoug.com.core.util

sealed class UiEvents {

    data class  Navigate(val route : String) : UiEvents ()
    object NavigateUp : UiEvents ()
    data class ShowSnackBar(val message : UiText) : UiEvents()




}