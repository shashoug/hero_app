package shashoug.com.tracker_domain.use_case

import shashoug.com.tracker_domain.model.TrackedFood

data class TrackerUseCases(
    val trackFood: TrackFood,
    val searchFood: SearchFood,
    val deleteTrackedFood: DeleteTrackedFood,
    val calculateMealNutrients: CalculateMealNutrients,
    val getFoodForDate: GetFoodForDate
)
