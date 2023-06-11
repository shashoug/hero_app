package shashoug.com.onboarding_presentation.height

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
class HeightViewModel @Inject constructor(
    private  val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {

    var height by mutableStateOf("100")



    private val _uiEvent = Channel<UiEvents>()
    val uiEvents = _uiEvent.receiveAsFlow()

    fun  onHeightChange(height : String) {
        if (height.length <= 3){
            this.height = filterOutDigits(height)
        }
    }

    fun onNextClick() {
        viewModelScope.launch {
            val heightNumber = height.toIntOrNull() ?: kotlin.run {
                _uiEvent.send(
                    UiEvents.ShowSnackBar(UiText.DynamicString("Height should not be empty"))
                )
                return@launch
            }
            preferences.saveHeight(heightNumber)
            _uiEvent.send(UiEvents.Navigate(Routes.WEIGHT))

        }

    }

}