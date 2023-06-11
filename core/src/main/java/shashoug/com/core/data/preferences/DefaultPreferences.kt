package shashoug.com.core.data.preferences

import android.content.SharedPreferences
import shashoug.com.core.domin.model.ActivityLevel
import shashoug.com.core.domin.model.Gender
import shashoug.com.core.domin.model.GoalType
import shashoug.com.core.domin.model.UserInfo
import shashoug.com.core.domin.preferences.Preferences

class DefaultPreferences(
    private  val sharedPre : SharedPreferences
) : Preferences {
    override fun saveGender(gender: Gender) {
        sharedPre.edit()
            .putString(Preferences.KEY_GENDER,gender.name)
            .apply()
    }

    override fun saveAge(age: Int) {
        sharedPre.edit()
            .putInt(Preferences.KEY_AGE,age)
            .apply()
    }

    override fun saveWeight(weight: Float) {
        sharedPre.edit()
            .putFloat(Preferences.KEY_WEIGHT,weight)
            .apply()
    }

    override fun saveHeight(height: Int) {
        sharedPre.edit()
            .putInt(Preferences.KEY_HEIGHT,height)
            .apply()
    }

    override fun saveActivityLevel(level: ActivityLevel) {
        sharedPre.edit()
            .putString(Preferences.KEY_ACTIVITY_LEVEL,level.name)
            .apply()
    }

    override fun saveGoalType(type: GoalType) {
        sharedPre.edit()
            .putString(Preferences.KEY_GOAL_TYPE,type.name)
            .apply()
    }

    override fun saveCarbRatio(ratio: Float) {
        sharedPre.edit()
            .putFloat(Preferences.KEY_CARB_RATIO,ratio)
            .apply()
    }

    override fun saveProteinRatio(ratio: Float) {
        sharedPre.edit()
            .putFloat(Preferences.KEY_PROTEIN_RATIO,ratio)
            .apply()
    }

    override fun saveFatRatio(ratio: Float) {
        sharedPre.edit()
            .putFloat(Preferences.KEY_FAT_RATIO,ratio)
            .apply()
    }

    override fun loadUserInfo(): UserInfo {
        val age = sharedPre.getInt(Preferences.KEY_AGE, -1)
        val gender = sharedPre.getString(Preferences.KEY_GENDER, null)
        val weight = sharedPre.getFloat(Preferences.KEY_WEIGHT, -1f)
        val height = sharedPre.getInt(Preferences.KEY_HEIGHT, -1)
        val goalType = sharedPre.getString(Preferences.KEY_GOAL_TYPE, null)
        val activityLevel = sharedPre.getString(Preferences.KEY_ACTIVITY_LEVEL, null)
        val carbRatio = sharedPre.getFloat(Preferences.KEY_CARB_RATIO, -1f)
        val fatRatio = sharedPre.getFloat(Preferences.KEY_FAT_RATIO, -1f)
        val proteinRatio = sharedPre.getFloat(Preferences.KEY_PROTEIN_RATIO, -1f)

        return  UserInfo(
            gender = Gender.fromString(gender ?: "male"),
            age = age,
            weight = weight,
            height = height,
            activityLevel = ActivityLevel.fromString(activityLevel ?: "medium"),
            goalType =  GoalType.fromString(goalType ?: "keep_weight"),
            carbRatio = carbRatio,
            proteinRation = proteinRatio,
            fatRatio = fatRatio
        )


    }

}