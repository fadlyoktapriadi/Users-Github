package com.example.core.di


import androidx.room.Room
import com.example.core.BuildConfig
import com.example.core.data.UsersRepository
import com.example.core.data.api.ApiService
import com.example.core.data.api.RemoteDataSource
import com.example.core.data.local.LocalDataSource
import com.example.core.data.local.database.UserRoomDatabase
import com.example.core.domain.repository.IUserFavRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val API_KEY = BuildConfig.KEY
private const val BASE_URL = BuildConfig.BaseURL

val databaseModule = module {
    factory {
        get<UserRoomDatabase>().usersDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            UserRoomDatabase::class.java, "Users"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", API_KEY)
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IUserFavRepository> {
        UsersRepository(
            get(),
            get()
        )
    }
}