package global.x.weather.infrastructure.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import global.x.weather.XWeatherApplication

@InstallIn(SingletonComponent::class)
@Module
class ApplicationModule {

    @Provides
    fun providesXWeatherApplication(@ApplicationContext context: Context): XWeatherApplication {
        return context as XWeatherApplication
    }
}