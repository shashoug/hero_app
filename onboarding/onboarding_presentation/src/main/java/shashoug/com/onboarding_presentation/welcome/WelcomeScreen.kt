package shashoug.com.onboarding_presentation.welcome

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import shashoug.com.core.navigation.Routes
import shashoug.com.core.util.UiEvents
import shashoug.com.coreui.LocalSpacing
import shashoug.com.onboarding_presentation.components.ActionButton

@Composable

fun WelcomeScreen(
    onNavigate : (UiEvents.Navigate) -> Unit
){
val spacing = LocalSpacing.current
    Column (
        modifier =  Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
            ) {
            Text(
                text = "Welcome to Hero Calories Calculator",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h1

            )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))

        ActionButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Next",
            onClick = { onNavigate(UiEvents.Navigate(Routes.GENDER)) }
        )

    }
}