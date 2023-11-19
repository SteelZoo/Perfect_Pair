package com.olduo.last_dance.modules

import com.olduo.last_dance.data.datasource.PERFECT_PAIR_BASE_URL
import com.olduo.last_dance.data.datasource.remote.service.UserService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun moshi() = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    @Provides
    @Singleton
    fun providesExchangeRetrofitClient(
        moshi: Moshi
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(PERFECT_PAIR_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService{
        return retrofit.create(UserService::class.java)
    }
}