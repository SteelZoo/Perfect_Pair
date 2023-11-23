package com.ssafy.template.board.config

import android.util.Log
import com.olduo.last_dance.preseatation.SharedPreferencesUtil
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

private const val TAG = "ReceivedCookies_싸피"

class ReceivedCookiesInterceptor(
    private val sharedPreferencesUtil: SharedPreferencesUtil
) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalResponse: Response = chain.proceed(chain.request())

        if (originalResponse.headers("Set-Cookie").isNotEmpty()) {

            val cookies = HashSet<String>()
            for (cookie in originalResponse.headers("Set-Cookie")) {
                cookies.add(cookie)
                Log.d(TAG, "intercept: $cookie")
            }

            // cookie 내부 데이터에 저장
            sharedPreferencesUtil.addUserCookie(cookies)
            Log.d(TAG, "intercept: ${sharedPreferencesUtil.getUserCookie()?.toString()}")
        }
        return originalResponse
    }
}