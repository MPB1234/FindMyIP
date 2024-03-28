package com.photon.findmyip.di

import com.photon.findmyip.FindIPApiService
import com.photon.findmyip.repository.FindIpRepository
import com.photon.findmyip.repository.FindIpRepositoryImpl
import com.photon.findmyip.viewmodel.FindIpViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitProviderModule {

    private val BASE_URL = "https://ipapi.co/"

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): FindIPApiService {
        return retrofit.create(FindIPApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: FindIPApiService): FindIpRepository {
        return FindIpRepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesViewModel(repo: FindIpRepositoryImpl): FindIpViewModel {
        return FindIpViewModel(repo)
    }

    @Provides
    @Singleton
    fun provideOkHTTP(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL).client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}