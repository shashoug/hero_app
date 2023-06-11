package shashoug.com.onboarding_presentation.goal_type

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import shashoug.com.core.domin.model.ActivityLevel
import shashoug.com.core.domin.model.Gender
import shashoug.com.core.domin.model.GoalType
import shashoug.com.core.util.UiEvents
import shashoug.com.coreui.LocalSpacing
import shashoug.com.onboarding_presentation.activity_type.ActivityViewModel
import shashoug.com.onboarding_presentation.components.ActionButton
import shashoug.com.onboarding_presentation.components.SelectableButton
import shashoug.com.onboarding_presentation.gender.GenderViewModel

@Composable

fun GoalTypeScreen(
    onNavigate : (UiEvents.Navigate) -> Unit,
    viewModel: GoalTypeViewModel = hiltViewModel()

) {
    val spacing = LocalSpacing.current
    LaunchedEffect(key1 = true){
        viewModel.uiEvents.collect{ event ->
            when(event) {
                is UiEvents.Navigate -> onNavigate(event)
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
                text = "What's your Goal?",
                style = MaterialTheme.typography.h3
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    text = "Gain Weight",
                    isSelected = viewModel.selectedGoal is GoalType.GainWeight ,
                    color = MaterialTheme.colors.primaryVariant ,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onLevelClick(GoalType.GainWeight)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )

                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))

                SelectableButton(
                    text = "Keep Weight",
                    isSelected = viewModel.selectedGoal is GoalType.KeepWeight,
                    color = MaterialTheme.colors.primaryVariant ,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onLevelClick(GoalType.KeepWeight)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )

                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = "Lose Weight",
                    isSelected = viewModel.selectedGoal is GoalType.LoseWeight,
                    color = MaterialTheme.colors.primaryVariant ,
                    selectedTextColor = Color.White,
                    onClick = {
                        viewModel.onLevelClick(GoalType.LoseWeight)
                    },
                    textStyle = MaterialTheme.typography.button.copy(
                        fontWeight = FontWeight.Normal
                    )

                )
            }
        }
        ActionButton(
            modifier = Modifier.align(Alignment.BottomEnd),
            text = "Next",
            onClick = viewModel::onNextClick,

            )

    }



}