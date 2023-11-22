package com.olduo.last_dance.modules

import com.olduo.last_dance.data.datasource.PERFECT_PAIR_BASE_URL
import com.olduo.last_dance.data.datasource.remote.service.AnswerService
import com.olduo.last_dance.data.datasource.remote.service.GroupService
import com.olduo.last_dance.data.datasource.remote.service.QuizSevice
import com.olduo.last_dance.data.datasource.remote.service.UserService
import com.olduo.last_dance.preseatation.SharedPreferencesUtil
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ssafy.template.board.config.AddCookiesInterceptor
import com.ssafy.template.board.config.ReceivedCookiesInterceptor
import com.ssafy.template.board.config.XAccessTokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
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
        moshi: Moshi,
        sharedPreferencesUtil: SharedPreferencesUtil
    ): Retrofit {
        val nullOnEmptyConverterFactory = object : Converter.Factory() {
            fun converterFactory() = this
            override fun responseBodyConverter(
                type: Type,
                annotations: Array<out Annotation>,
                retrofit: Retrofit
            ) = object : Converter<ResponseBody, Any?> {
                val nextResponseBodyConverter =
                    retrofit.nextResponseBodyConverter<Any?>(converterFactory(), type, annotations)

                override fun convert(value: ResponseBody) =
                    if (value.contentLength() != 0L) nextResponseBodyConverter.convert(value) else null
            }
        }
        val client: OkHttpClient = OkHttpClient.Builder()
            .readTimeout(5000, TimeUnit.MILLISECONDS)
            .connectTimeout(5000, TimeUnit.MILLISECONDS)
            // 로그캣에 okhttp.OkHttpClient로 검색하면 http 통신 내용을 보여줍니다.
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .addNetworkInterceptor(XAccessTokenInterceptor(sharedPreferencesUtil)) // JWT 자동 헤더 전송
            .addInterceptor(AddCookiesInterceptor(sharedPreferencesUtil))  //쿠키 전송
            .addInterceptor(ReceivedCookiesInterceptor(sharedPreferencesUtil)) //쿠키 추출
            .build()

        return Retrofit.Builder()
            .baseUrl(PERFECT_PAIR_BASE_URL)
            .client(client)
            .addConverterFactory(nullOnEmptyConverterFactory)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

    @Provides
    @Singleton
    fun provideGroupService(retrofit: Retrofit): GroupService {
        return retrofit.create(GroupService::class.java)
    }

    @Provides
    @Singleton
    fun provideQuizService(retrofit: Retrofit): QuizSevice {
        return retrofit.create(QuizSevice::class.java)
    }

    @Provides
    @Singleton
    fun provideAnswerService(retrofit: Retrofit): AnswerService {
        return retrofit.create(AnswerService::class.java)
    }
}