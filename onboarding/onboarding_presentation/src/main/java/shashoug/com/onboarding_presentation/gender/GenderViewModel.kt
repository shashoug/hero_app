package shashoug.com.onboarding_presentation.gender

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import shashoug.com.core.domin.model.Gender
import shashoug.com.core.domin.preferences.Preferences
import shashoug.com.core.navigation.Routes
import shashoug.com.core.util.UiEvents

import javax.inject.Inject


@HiltViewModel
class GenderViewModel @Inject constructor(
    private  val preferences: Preferences
) : ViewModel(){

    var selectedGender by mutableStateOf<Gender>(Gender.Male)
    private  set
    private val _uiEvent = Channel<UiEvents>()
    val uiEvents = _uiEvent.receiveAsFlow()

    fun onGenderClick(gender : Gender){
        selectedGender = gender
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveGender(selectedGender)
            _uiEvent.send(UiEvents.Navigate(Routes.AGE))

        }

    }

}