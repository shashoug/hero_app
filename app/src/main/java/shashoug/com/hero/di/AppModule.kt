package shashoug.com.hero.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import shashoug.com.core.data.preferences.DefaultPreferences
import shashoug.com.core.domin.preferences.Preferences
import shashoug.com.core.domin.use_case.FilterOutDigits
import shashoug.com.onboarding_domain.use_case.ValidateNutrients
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(
        app: Application
    ) : SharedPreferences {
        return  app.getSharedPreferences("shared_prefs", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferences(
        sharedPreferences: SharedPreferences)
    : Preferences {
        return  DefaultPreferences(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideFilterOutDigitsUseCase() : FilterOutDigits{
           return  FilterOutDigits()
    }
}