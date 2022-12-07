package com.udacity.asteroidradar

import android.app.Application
import androidx.room.Room
import androidx.work.*
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.datasource.local.*
import com.udacity.asteroidradar.datasource.remote.AsteroidApi
import com.udacity.asteroidradar.datasource.repo.AsteroidRepo
import com.udacity.asteroidradar.datasource.repo.IAsteroidRepo
import com.udacity.asteroidradar.ui.main.MainViewModel
import com.udacity.asteroidradar.utils.Constants
import com.udacity.asteroidradar.worker.AsteroidWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

class App : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
        startWorker()
    }

    private fun startWorker() {
        applicationScope.launch {
            val workRequest = PeriodicWorkRequestBuilder<AsteroidWorker>(1, TimeUnit.DAYS)
                .setConstraints(
                    Constraints.Builder()
                        .setRequiredNetworkType(NetworkType.CONNECTED)
                        .setRequiresCharging(true)
                        .build()

                )
                .build()

            WorkManager.getInstance(applicationContext).enqueueUniquePeriodicWork(
                AsteroidWorker::class.java.simpleName,
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
        }
    }
}

val appModule = module {
    viewModel {
        MainViewModel(get())
    }
    single<AsteroidLocalDataSource> { AsteroidLocalDataSourceImp(get()) }
    single<AsteroidRemoteDataSource> { AsteroidRemoteDataSourceImp(get()) }
    single<IAsteroidRepo> { AsteroidRepo(get(), get()) }
    single { AsteroidDatabase.getInstance(androidContext()).asteroidDao }
    single {
        Room.databaseBuilder(
            androidContext().applicationContext,
            AsteroidDatabase::class.java,
            AsteroidDatabase::class.java.simpleName
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        OkHttpClient().newBuilder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(
                HttpLoggingInterceptor().apply {
                    setLevel(
                        if (BuildConfig.DEBUG)
                            HttpLoggingInterceptor.Level.BODY
                        else
                            HttpLoggingInterceptor.Level.NONE
                    )
                }
            )
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(get())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(get()))
            .build()

    }
    single {
        get<Retrofit>().create(AsteroidApi::class.java)
    }

}