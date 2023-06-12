package shashoug.com.tracker_domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import shashoug.com.core.domin.preferences.Preferences
import shashoug.com.tracker_domain.model.TrackedFood
import shashoug.com.tracker_domain.repository.TrackerRepository
import shashoug.com.tracker_domain.use_case.CalculateMealNutrients
import shashoug.com.tracker_domain.use_case.DeleteTrackedFood
import shashoug.com.tracker_domain.use_case.GetFoodForDate
import shashoug.com.tracker_domain.use_case.SearchFood
import shashoug.com.tracker_domain.use_case.TrackFood
import shashoug.com.tracker_domain.use_case.TrackerUseCases

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUserCases(
        repository: TrackerRepository,
        preferences: Preferences
    ) : TrackerUseCases {
        return  TrackerUseCases(
            trackFood = TrackFood(repository),
            searchFood = SearchFood(repository),
            deleteTrackedFood = DeleteTrackedFood(repository),
            calculateMealNutrients = CalculateMealNutrients(preferences),
            getFoodForDate = GetFoodForDate(repository)

        )
    }
}