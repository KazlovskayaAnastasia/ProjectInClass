package net

import com.google.gson.GsonBuilder
import entity.*
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

//для загрузки и получения
class RestService(private val apiUrl: String) {

    private val restApi: RestApi
    private val restParser: RestErrorParser

    init {
        val okHttpBuilder = OkHttpClient.Builder()
                .readTimeout(30, TimeUnit.SECONDS)//сколько времени на чтение данных
                .writeTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(30, TimeUnit.SECONDS)

        okHttpBuilder.addInterceptor(HttpLoggingInterceptor())

        val gson = GsonBuilder().create()
        restParser = RestErrorParser(gson)

        val retrofit = Retrofit.Builder()
                .baseUrl(apiUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //ответы в виде rx
                .addConverterFactory(GsonConverterFactory.create(gson))//как делать парсинг данных
                .client(okHttpBuilder.build())
                .build()

        restApi = retrofit.create(RestApi::class.java)
    }

    fun getStudents() : Observable<List<StudentResponse>>{
        return restApi.getStudents()
                .compose(restParser.parseError())
                .onErrorReturn {
                    if (it is SocketTimeoutException){
                        throw AppException(AppErrorType.SERVER_IS_NOT_AVAILABLE)
                    }else{
                        throw AppException(AppErrorType.UNKNOWN_EXCEPTION)
                    }
                }
    }

    fun getStudentsById(id:String) : Observable<StudentResponse>{
        return restApi.getStudentsById(id)
                .compose(restParser.parseError())
    }

    fun updateStudent(student : StudentRequest) : Observable<StudentResponse>{
        return restApi.updateStudent(student)
                .compose(restParser.parseError())
    }

    fun deleteStudent(id:String) : Observable<Void>{
        return restApi.deleteStudent(id)
                .compose(restParser.parseError())
    }

    fun login(login:LoginRequest) : Observable<Token>{
        return restApi
                .login(login)
                .compose(restParser.parseError())
    }
}