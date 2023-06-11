package shashoug.com.onboarding_presentation.age

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import shashoug.com.core.domin.preferences.Preferences
import shashoug.com.core.domin.use_case.FilterOutDigits
import shashoug.com.core.navigation.Routes
import shashoug.com.core.util.UiEvents
import shashoug.com.core.util.UiText
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private  val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {

   var age by mutableStateOf("22")



    private val _uiEvent = Channel<UiEvents>()
    val uiEvents = _uiEvent.receiveAsFlow()

    fun  onAgeChange(age : String) {
        if (age.length <= 3){
            this.age = filterOutDigits(age)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val ageNumber = age.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvents.ShowSnackBar(UiText.DynamicString("Age should not be empty"))
                )
                return@launch
            }
            preferences.saveAge(ageNumber)
            _uiEvent.send(UiEvents.Navigate(Routes.HEIGHT))

        }

    }

}