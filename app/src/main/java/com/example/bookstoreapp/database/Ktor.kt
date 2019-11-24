package com.example.bookstoreapp.database

import io.ktor.client.HttpClient
import io.ktor.client.call.call
import io.ktor.client.request.url
import io.ktor.client.response.HttpResponse
import io.ktor.http.HttpMethod
import io.ktor.http.isSuccess
import io.ktor.util.cio.writeChannel
import kotlinx.coroutines.io.copyAndClose
import kotlinx.io.errors.IOException
import java.io.File
import java.net.URL


data class HttpClientException(val response: HttpResponse) : IOException("HTTP Error ${response.status}")

suspend fun HttpClient.getAsTempFile(url: String, callback: suspend (file: File) -> Unit) {
    val file = getAsTempFile(url)
    try {
        callback(file)
    } finally {
        file.delete()
    }
}

suspend fun HttpClient.getAsTempFile(url: String): File {
    val file = File.createTempFile("ktor", "http-client")
    val call = call {
        url(URL(url))
        method = HttpMethod.Get
    }
    if (!call.response.status.isSuccess()) {
        throw HttpClientException(call.response)
    }
    call.response.content.copyAndClose(file.writeChannel())
    return file
}