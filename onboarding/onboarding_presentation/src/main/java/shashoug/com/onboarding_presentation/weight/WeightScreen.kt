package shashoug.com.onboarding_presentation.weight
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import shashoug.com.core.util.UiEvents
import shashoug.com.coreui.LocalSpacing
import shashoug.com.onboarding_presentation.components.ActionButton
import shashoug.com.onboarding_presentation.components.UnitTextField

@Composable

fun WeightScreen(
    scaffoldState: ScaffoldState,
    onNavigate : (UiEvents.Navigate) -> Unit,
    viewModel : WeightViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    LaunchedEffect(key1 = true){
        viewModel.uiEvents.collect{ event ->
            when(event) {
                is UiEvents.Navigate -> onNavigate(event)
                is UiEvents.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }
                else -> Unit
            }
        }
    }

    Box (
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge))
    {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "What is your Weight?",
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(value = viewModel.weight, onValueChange = viewModel::onWeightChange, unit = "kg" , color = MaterialTheme.colors.primaryVariant )
        }
        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = "Next",
            onClick = viewModel::onNextClick,

            )

    }



}