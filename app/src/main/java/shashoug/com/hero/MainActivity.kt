package shashoug.com.hero

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import shashoug.com.core.navigation.Routes
import shashoug.com.hero.navigation.navigate
import shashoug.com.hero.ui.theme.HeroTheme
import shashoug.com.onboarding_presentation.activity_type.ActivityScreen
import shashoug.com.onboarding_presentation.age.AgeScreen
import shashoug.com.onboarding_presentation.gender.GenderScreen
import shashoug.com.onboarding_presentation.goal_type.GoalTypeScreen
import shashoug.com.onboarding_presentation.height.HeightScreen
import shashoug.com.onboarding_presentation.nutrient_goal.NutrientGoalScreen
import shashoug.com.onboarding_presentation.weight.WeightScreen
import shashoug.com.onboarding_presentation.welcome.WelcomeScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HeroTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val scaffoldState  = rememberScaffoldState()
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState) {
                    NavHost(navController = navController,
                        startDestination = Routes.WELCOME ){

                        composable(Routes.WELCOME){
                            WelcomeScreen(onNavigate = navController::navigate)
                        }
                        composable(Routes.GENDER){
                            GenderScreen(onNavigate = navController::navigate)

                        }
                        composable(Routes.AGE){
                            AgeScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )

                        }
                        composable(Routes.WEIGHT){
                            WeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Routes.HEIGHT){
                            HeightScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }
                        composable(Routes.NUTRIENT_GOAL){
                            NutrientGoalScreen(
                                scaffoldState = scaffoldState,
                                onNavigate = navController::navigate
                            )
                        }

                        composable(Routes.TRACKER){

                        }
                        composable(Routes.SEARCH){

                        }
                        composable(Routes.ACTIVITY){
                            ActivityScreen(onNavigate = navController::navigate)
                        }
                        composable(Routes.GOAL){
                            GoalTypeScreen(onNavigate = navController::navigate)

                        }

                    }


                  }
                }
            }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HeroTheme {
        Greeting("Android")
    }
}