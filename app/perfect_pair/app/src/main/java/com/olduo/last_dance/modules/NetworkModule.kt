package com.olduo.last_dance.modules

import com.olduo.last_dance.data.datasource.PERFECT_PAIR_BASE_URL
import com.olduo.last_dance.data.datasource.remote.service.AnswerService
import com.olduo.last_dance.data.datasource.remote.service.GroupService
import com.olduo.last_dance.data.datasource.remote.service.QuizSevice
import com.olduo.last_dance.data.datasource.remote.service.UserService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
        val nullOnEmptyConverterFactory = object : Converter.Factory() {
            fun converterFactory() = this
            override fun responseBodyConverter(type: Type, annotations: Array<out Annotation>, retrofit: Retrofit) = object : Converter<ResponseBody, Any?> {
                val nextResponseBodyConverter = retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)
                override fun convert(value: ResponseBody) = if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
            }
        }
        return Retrofit.Builder()
            .baseUrl(PERFECT_PAIR_BASE_URL)
            .addConverterFactory(nullOnEmptyConverterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService{
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideGroupService(retrofit: Retrofit): GroupService{
        return retrofit.create(GroupService::class.java)
    }

    @Provides
    @Singleton
    fun provideQuizService(retrofit: Retrofit): QuizSevice{
        return retrofit.create(QuizSevice::class.java)
    }

    @Provides
    @Singleton
    fun provideAnswerService(retrofit: Retrofit): AnswerService{
        return retrofit.create(AnswerService::class.java)
    }
}