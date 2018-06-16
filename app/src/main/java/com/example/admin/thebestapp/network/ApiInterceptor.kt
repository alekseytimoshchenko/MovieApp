package com.example.admin.follower.network.global

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * Created with care by Alexey.T on 11/03/2018.
 *
 * TODO: Add a class header comment!
 */
@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class ApiInterceptor: Interceptor
{
    override fun intercept(chain: Interceptor.Chain): Response?
    {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url()
        
        val url: HttpUrl? = with(originalHttpUrl.newBuilder()) {
//            addQueryParameter("api_key", API.KEY.value)
            build()
        }
        
        val requestBuilder: Request.Builder = original.newBuilder().url(url)
        val request: Request? = requestBuilder.build()
        
        return chain.proceed(request)
    }
}