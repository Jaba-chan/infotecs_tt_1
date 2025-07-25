package ru.evgenykuzakov.network

import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody

class MockErrorInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val mockJson = """
            {
              "error": "Uh oh, something has gone wrong. Please tweet us @randomapi about the issue. Thank you."
            }
        """.trimIndent()
        val body = ResponseBody.create(MediaType.get("application/json"), mockJson)

        return Response.Builder()
            .code(500)
            .message("Inner server error")
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .body(body)
            .addHeader("content-type", "application/json")
            .build()
    }
}