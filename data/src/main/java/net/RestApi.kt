package net

import entity.LoginRequest
import entity.StudentRequest
import entity.StudentResponse
import entity.Token
import io.reactivex.Observable
import retrofit2.http.*

//логика запросов
interface RestApi {

    @POST("login")
    fun login(@Body body: LoginRequest): Observable<Token>

    @GET("")
    fun getStudents(): Observable<List<StudentResponse>>

    @GET("students/{id}/")
    fun getStudentsById(@Path("id")id: String): Observable<StudentResponse>

    @POST("student/")
    fun updateStudent(@Body student: StudentRequest):Observable<StudentResponse>

    @DELETE ("students/{id}/")
    fun deleteStudent(@Path("id")id: String):Observable<Void>
}