package shashoug.com.onboarding_presentation.goal_type
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import shashoug.com.core.domin.model.GoalType
import shashoug.com.core.domin.preferences.Preferences
import shashoug.com.core.navigation.Routes
import shashoug.com.core.util.UiEvents
import javax.inject.Inject


@HiltViewModel
class GoalTypeViewModel @Inject constructor(
    private  val preferences: Preferences
) : ViewModel() {

    var selectedGoal by mutableStateOf<GoalType>(GoalType.KeepWeight)
        private set
    private val _uiEvent = Channel<UiEvents>()
    val uiEvents = _uiEvent.receiveAsFlow()

    fun onLevelClick(type: GoalType) {
        selectedGoal = type
    }

    fun onNextClick() {
        viewModelScope.launch {
            preferences.saveGoalType(selectedGoal)
            _uiEvent.send(UiEvents.Navigate(Routes.NUTRIENT_GOAL))

        }

    }
}

