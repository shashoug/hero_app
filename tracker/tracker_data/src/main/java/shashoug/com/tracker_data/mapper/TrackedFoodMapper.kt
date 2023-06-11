package shashoug.com.tracker_data.mapper

import android.annotation.SuppressLint
import shashoug.com.tracker_data.local.entity.TrackedFoodEntity
import shashoug.com.tracker_domain.model.MealType
import shashoug.com.tracker_domain.model.TrackedFood
import java.time.LocalDate

@SuppressLint("NewApi")
fun TrackedFoodEntity.toTrackedFood(): TrackedFood {
    return TrackedFood(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        mealType = MealType.fromString(type),
        amount = amount,
        date = LocalDate.of(year, month, dayOfMonth),
        calories = calories,
        id = id
    )
}

@SuppressLint("NewApi")
fun TrackedFood.toTrackedFoodEntity(): TrackedFoodEntity {
    return TrackedFoodEntity(
        name = name,
        carbs = carbs,
        protein = protein,
        fat = fat,
        imageUrl = imageUrl,
        type = mealType.name,
        amount = amount,
        dayOfMonth = date.dayOfMonth,
        month = date.monthValue,
        year = date.year,
        calories = calories,
        id = id
    )
}