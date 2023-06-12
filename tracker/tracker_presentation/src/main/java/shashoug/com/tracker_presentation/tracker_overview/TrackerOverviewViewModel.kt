package shashoug.com.tracker_presentation.tracker_overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import shashoug.com.core.domin.preferences.Preferences
import shashoug.com.core.navigation.Routes
import shashoug.com.core.util.UiEvents
import shashoug.com.tracker_domain.use_case.TrackerUseCases
import javax.inject.Inject

@HiltViewModel
class TrackerOverviewViewModel  @Inject constructor(
    preferences: Preferences,
    private val trackerUseCases: TrackerUseCases
) : ViewModel() {

    private val _uiEvent = Channel<UiEvents>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var state by mutableStateOf(TrackerOverviewState())
        private set
private var getFoodsForDateJob : Job? = null
    init {
      preferences.saveShouldShowOnboarding(false)
    }
    fun onEvent(event: TrackerOverviewEvent){
        when(event){
            is TrackerOverviewEvent.OnAddFoodClick -> {
                viewModelScope.launch {
                    _uiEvent.send(
                        UiEvents.Navigate(
                            route = Routes.SEARCH
                        + "/${event.meal.mealType.name}"
                        + "/${state.date.dayOfMonth}"
                        + "/${state.date.monthValue}"
                        + "/${state.date.year}"
                        )
                    )
                }
            }
            is TrackerOverviewEvent.OnDeleteTrackedFoodClick -> {
                viewModelScope.launch {
                    trackerUseCases.deleteTrackedFood(event.trackedFood)
                    refreshFood()
                }

            }
            is TrackerOverviewEvent.OnNextDayClick ->{
               state.copy(
                   date = state.date.plusDays(1)
               )
               refreshFood()
            }
            is TrackerOverviewEvent.OnPreviousDayClick ->{
                state.copy(
                    date = state.date.minusDays(1)
                )
                refreshFood()
            }
            is TrackerOverviewEvent.OnToggleMealClick ->{
                state = state.copy(
                    meals = state.meals.map {
                        if(it.name == event.meal.name){
                            it.copy(isExpanded =  !it.isExpanded)
                        } else it
                    }
                )
            }
        }
    }
    private fun refreshFood() {
        getFoodsForDateJob?.cancel()
      getFoodsForDateJob =  trackerUseCases
            .getFoodForDate(state.date)
            .onEach { foods ->
                val nutrientsResult = trackerUseCases.calculateMealNutrients(foods)
                state = state.copy(
                    totalCarbs = nutrientsResult.totalCarbs,
                    totalCalories = nutrientsResult.totalCalories,
                    totalFat =  nutrientsResult.totalFat,
                    totalProtein = nutrientsResult.totalProtein,
                    caloriesGoal = nutrientsResult.caloriesGoal,
                    carbsGoal = nutrientsResult.carbsGoal,
                    proteinGoal = nutrientsResult.proteinGoal,
                    fatGoal = nutrientsResult.fatGoal,
                    date = state.date,
                    trackedFoods = foods,
                    meals = state.meals.map {
                        val nutrientsForMeal =
                            nutrientsResult.mealNutrients[it.mealType]
                        ?: return@map it.copy(
                        carbs = 0,
                        protein = 0,
                            fat = 0,
                            calories = 0
,                        )
                        it.copy(
                            carbs = nutrientsForMeal.carbs,
                            protein = nutrientsForMeal.protein,
                            fat =  nutrientsForMeal.fat,
                            calories = nutrientsForMeal.calories
                        )
                    }


                )
            }.launchIn(viewModelScope)
    }

}