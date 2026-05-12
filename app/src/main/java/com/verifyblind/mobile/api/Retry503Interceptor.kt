package com.verifyblind.mobile.api

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * HTTP 503 (Service Unavailable) durumunda 1 kez tekrar dener.
 * Cloudflare veya altyapı kaynaklı geçici 503'leri tolere eder.
 */
class Retry503Interceptor : Interceptor {

    companion object {
        private const val MAX_RETRIES = 1
        private const val RETRY_DELAY_MS = 1000L
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)

        var retryCount = 0
        while (response.code == 503 && retryCount < MAX_RETRIES) {
            retryCount++
            response.close()
            Thread.sleep(RETRY_DELAY_MS)
            response = chain.proceed(request)
        }

        return response
    }
}
