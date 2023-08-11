package global.x.weather.infrastructure.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import global.x.weather.data.repositories.DeviceRepositoryImpl
import global.x.weather.data.repositories.WeatherRepositoryImpl
import global.x.weather.domain.use_cases.device.DeviceRepository
import global.x.weather.domain.use_cases.weather.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @Singleton
    fun bindWeatherRepository(weatherRepository: WeatherRepositoryImpl): WeatherRepository

    @Binds
    @Singleton
    fun bindDeviceRepository(deviceRepository: DeviceRepositoryImpl): DeviceRepository

}