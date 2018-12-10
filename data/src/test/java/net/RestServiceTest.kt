package net

import entity.AppErrorType
import entity.AppException
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RestServiceTest {

    fun testServerError(){

        val server =  MockWebServer()
        val response = MockResponse()

        response.setResponseCode(400)
        response.setBody("{\"message\": \"Тестовая ошибка\", \"errorCode\": 123}")
        server.enqueue(response)
        server.start()

        val url = server.url("/api/")

        val restService = RestService(url.toString())

        restService
                .getStudents()
                .test()
                .assertError{
                    it is AppException && it.errorType == AppErrorType.INCORRECT_ID
                }

    }
}