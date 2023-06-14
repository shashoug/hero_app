package shashoug.com.tracker_presentation.tracker_overview

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import shashoug.com.core.util.UiEvents
import shashoug.com.coreui.LocalSpacing
import shashoug.com.tracker_presentation.components.DaySelector
import shashoug.com.tracker_presentation.components.ExpandableMeal

@Composable
fun TrackerOverviewScreen(
   onNavigate : (UiEvents.Navigate) -> Unit,
   viewModel: TrackerOverviewViewModel = hiltViewModel()
){
   val spacing = LocalSpacing.current
   val context = LocalContext.current
   val state = viewModel.state
   LazyColumn(
       modifier = Modifier
           .fillMaxSize()
           .padding( bottom = spacing.spaceMedium )
   ){
       item { 
           NutrientsHeader(state = state)
           Spacer(modifier = Modifier
               .height(spacing.spaceMedium))
           DaySelector(
               date = state.date ,
               onPreviousDayClick = {
                   viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClick)
               },
               onNextDayClick = {
                   viewModel.onEvent(TrackerOverviewEvent.OnNextDayClick)
               },
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(horizontal = spacing.spaceMedium)
           )
           Spacer(modifier = Modifier
               .height(spacing.spaceMedium))
       }
       items(state.meals){ meal ->
           ExpandableMeal(
               meal = meal,
               onToggleClick = { viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClick(meal)) },
               content = { },
               modifier = Modifier.fillMaxWidth()
           )
       }
   }
}