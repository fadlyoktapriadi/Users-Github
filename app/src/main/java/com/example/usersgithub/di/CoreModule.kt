package com.example.usersgithub.di


import androidx.room.Room
import com.example.usersgithub.data.UsersRepository
import com.example.usersgithub.data.api.ApiService
import com.example.usersgithub.data.api.RemoteDataSource
import com.example.usersgithub.data.local.LocalDataSource
import com.example.usersgithub.data.local.database.UserRoomDatabase
import com.example.usersgithub.domain.repository.IUserFavRepository
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val API_KEY = "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"
private const val BASE_URL = "https://api.github.com/"

val databaseModule = module {
    factory {
        get<UserRoomDatabase>().usersDao()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            UserRoomDatabase::class.java, "Users.db"
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