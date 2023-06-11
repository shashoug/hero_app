package shashoug.com.core.domin.model

data class UserInfo(
val gender: Gender,
val weight : Float,
val height: Int,
val goalType: GoalType,
val activityLevel: ActivityLevel,
val age : Int,
val carbRatio: Float,
val proteinRation : Float,
val fatRatio : Float
)
