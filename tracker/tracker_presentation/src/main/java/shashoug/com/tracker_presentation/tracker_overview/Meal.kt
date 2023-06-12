package shashoug.com.tracker_presentation.tracker_overview

import androidx.annotation.DrawableRes
import shashoug.com.core.util.UiText
import shashoug.com.tracker_domain.model.MealType
import shashoug.com.core.R

data class Meal(
    val name : UiText,
@DrawableRes    val drawableRes : Int,
    val mealType: MealType,
    val carbs : Int = 0,
    val protein : Int = 0,
    val fat : Int = 0,
    val calories : Int = 0,
    val isExpanded : Boolean = false,

)

val defaultMeals = listOf(
    Meal(
        name = UiText.DynamicString("Breakfast"),
        drawableRes = R.drawable.ic_breakfast,
        mealType = MealType.Breakfast,
    ),
    Meal(
        name = UiText.DynamicString("Lunch"),
        drawableRes = R.drawable.ic_lunch,
        mealType = MealType.Lunch,
    ),
    Meal(
        name = UiText.DynamicString("Dinner"),
        drawableRes = R.drawable.ic_dinner,
        mealType = MealType.Dinner,
    ),
    Meal(
        name = UiText.DynamicString("Snacks"),
        drawableRes = R.drawable.ic_snack,
        mealType = MealType.Snack,
    ),
)