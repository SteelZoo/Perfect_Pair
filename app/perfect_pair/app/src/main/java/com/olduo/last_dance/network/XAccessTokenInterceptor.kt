package com.ssafy.template.board.config


import com.olduo.last_dance.preseatation.SharedPreferencesUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class XAccessTokenInterceptor(
    val sharedPreferencesUtil: SharedPreferencesUtil
) : Interceptor {

    private val X_ACCESS_TOKEN = "X-ACCESS-TOKEN"

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = sharedPreferencesUtil.getString(X_ACCESS_TOKEN)
        if (jwtToken != null) {
            builder.addHeader("X-ACCESS-TOKEN", jwtToken)
        }
        return chain.proceed(builder.build())
    }
}