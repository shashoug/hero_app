package shashoug.com.hero.navigation

import androidx.navigation.NavController
import shashoug.com.core.util.UiEvents


fun NavController.navigate(event: UiEvents.Navigate){
    this.navigate(event.route)
}