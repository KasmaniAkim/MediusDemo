package com.demo.medius.data.di

import com.google.gson.GsonBuilder
import com.demo.medius.BuildConfig
import com.demo.medius.data.retrofit.OTPRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

val networkModule = module {
    single { provideRetrofit() }
    single { OTPRepository(get()) }
}

fun provideRetrofit(): Retrofit {
    val gson = GsonBuilder()
        .setLenient()
        .create()
    return Retrofit.Builder().baseUrl(BuildConfig.CUSTOM_BASE_URL)
        .client(provideOkHttpClient())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson)).build()
}

fun provideOkHttpClient(): OkHttpClient {
    val builder = OkHttpClient()
        .newBuilder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
    val requestInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    builder.addNetworkInterceptor(requestInterceptor)


    return builder.build()
}