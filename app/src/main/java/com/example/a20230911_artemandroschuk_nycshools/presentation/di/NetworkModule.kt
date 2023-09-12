package com.example.a20230911_artemandroschuk_nycshools.presentation.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.a20230911_artemandroschuk_nycshools.data.api.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val GENERAL_TIMEOUT = 15L
private const val CONNECT_TIMEOUT = GENERAL_TIMEOUT
private const val WRITE_TIMEOUT = GENERAL_TIMEOUT
private const val READ_TIMEOUT = GENERAL_TIMEOUT
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun provideChuckerInterceptor(@ApplicationContext context: Context) =
        ChuckerInterceptor.Builder(context).build()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        chuckInterceptor: ChuckerInterceptor,
    ): OkHttpClient =
        OkHttpClient.Builder()
            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(chuckInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://data.cityofnewyork.us/resource/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}