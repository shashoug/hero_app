package shashoug.com.tracker_domain

import com.google.common.truth.Truth.assertThat
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import shashoug.com.core.domin.model.ActivityLevel
import shashoug.com.core.domin.model.Gender
import shashoug.com.core.domin.model.GoalType
import shashoug.com.core.domin.model.UserInfo
import shashoug.com.core.domin.preferences.Preferences
import shashoug.com.tracker_domain.model.MealType
import shashoug.com.tracker_domain.model.TrackedFood
import shashoug.com.tracker_domain.use_case.CalculateMealNutrients
import java.time.LocalDate
import kotlin.random.Random


class CalculateMealNutrientsTest {
    private lateinit var calculateMealNutrients: CalculateMealNutrients


    @Before
    fun setUp() {
        val preferences = mockk<Preferences>(relaxed = true)
        every { preferences.loadUserInfo() } returns UserInfo(
            gender = Gender.Male,
            age = 20,
            weight = 80f,
            height = 180,
            goalType = GoalType.LoseWeight,
            activityLevel = ActivityLevel.Medium,
            carbRatio = 0.4f,
            proteinRatio = 0.3f,
            fatRatio = 0.3f
        )
        calculateMealNutrients = CalculateMealNutrients(preferences)

    }


    @Test
    fun `Calories for breakfast well calculated` (){
        val trackedFoods = (1..30).map {
            TrackedFood(
                name = "nazar",
                carbs =  Random.nextInt(100),
                protein =  Random.nextInt(100),
                fat =  Random.nextInt(100),
                mealType = MealType.fromString(
                    listOf("breakfast","lunch","dinner","snack").random()
                ),
                imageUrl = null,
                amount = 100,
                date = LocalDate.now(),
                calories = Random.nextInt(2000)
            )
        }
       val result = calculateMealNutrients(trackedFoods)
        val breakfastCalories = result.mealNutrients.values.filter { it.mealType is MealType.Breakfast}.sumOf { it.calories}
        val expectedCalories = trackedFoods.filter { it.mealType is MealType.Breakfast}.sumOf { it.calories}

        assertThat(breakfastCalories).isEqualTo(expectedCalories)




    }



}