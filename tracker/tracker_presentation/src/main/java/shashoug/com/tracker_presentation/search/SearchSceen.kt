package shashoug.com.tracker_presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.flow.collect
import shashoug.com.core.util.UiEvents
import shashoug.com.coreui.LocalSpacing
import shashoug.com.tracker_presentation.search.components.SearchTextField

@OptIn(ExperimentalComposeUiApi::class)
@Composable

fun SearchScreen(
    onNavigateUp : () -> Unit,
    scaffoldState: ScaffoldState,
    mealName : String,
    dayOfMonth : Int,
    month : Int,
    year : Int,
    viewModel: SearchViewModel = hiltViewModel()
){
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    val state = viewModel.state
    val keyboardController  = LocalSoftwareKeyboardController.current

    LaunchedEffect(key1 = keyboardController){
        viewModel.uiEvent.collect{ event ->
            when(event) {
                is UiEvents.ShowSnackBar -> {
                    keyboardController?.hide()
                    scaffoldState.snackbarHostState.showSnackbar(
                        message =  event.message.asString(context)
                    )
                }
                is UiEvents.NavigateUp -> {onNavigateUp()}
                else -> Unit
            }

        }

    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium)

    ) {
      Text(
          text = "Add $mealName",
          style = MaterialTheme.typography.h2
      )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        SearchTextField(
            text =  state.query,
            onValueChange = {
                            viewModel.onEvent(SearchEvent.OnQueryChange(it))
            } ,
            onSearch = {
                       viewModel.onEvent(SearchEvent.OnSearch)
            },
            onFocusChanged = {
                viewModel.onEvent(SearchEvent.OnFocusSearchChange(isFocused = it.isFocused))
            }
        )
    }
}