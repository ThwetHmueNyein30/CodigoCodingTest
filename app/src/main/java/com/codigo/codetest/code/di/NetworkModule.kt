package com.codigo.codetest.code.di

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.codigo.codetest.code.data.api.MovieApi
import com.codigo.codetest.code.data.util.Constant
import com.codigo.codetest.code.data.util.Constant.API_KEY
import com.codigo.codetest.code.data.util.Constant.QUERY_API_KEY
import com.codigo.codetest.code.data.util.Constant.QUERY_LANGUAGE
import com.codigo.codetest.code.data.util.FlowCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder().client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(FlowCallAdapterFactory.create())
            .baseUrl(Constant.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            // Add any other customization or adapter factories if needed
            .build()
    }


    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .addInterceptor(Interceptor { chain ->
                var request: Request = chain.request()
                val url: HttpUrl =
                    request.url.newBuilder()
                        .addQueryParameter(QUERY_API_KEY, API_KEY)
                        .addQueryParameter(QUERY_LANGUAGE, "en-US")
                        .build()
                request = request.newBuilder().url(url).build()
                chain.proceed(request)
            })
            .build()
    }



}