package net

import com.google.gson.Gson
import entity.AppErrorType
import entity.AppException
import entity.ErrorResponse
import io.reactivex.ObservableTransformer
import retrofit2.HttpException
import java.lang.Exception
import java.net.SocketTimeoutException

class RestErrorParser(val gson: Gson) {

    fun <T> parseError(): ObservableTransformer<T, T> {
        return ObservableTransformer<T, T> { observable ->
            observable.onErrorReturn { throwable ->
                when (throwable) {
                    is HttpException -> {
                        val errorBody = throwable.response().errorBody()?.string()
                        if(errorBody == null){
                            throw AppException(AppErrorType.UNKNOWN_EXCEPTION)
                        }

                        try {
                            val errorObject = gson.fromJson<ErrorResponse>(errorBody, ErrorResponse::class.java)
                            when(errorObject.errorCode){
                                123 ->{ //например, неверный id студента
                                    throw AppException(AppErrorType.INCORRECT_ID)
                                }
                                222 ->{ //например, пользователь заблокирован
                                    throw AppException(AppErrorType.USER_BLOCKED)
                                }
                            }
                        }catch (e:Exception){
                            //отправить сообщение серверным разработчикам
                            //вызвать крашлитик
                        }
                        throw AppException(AppErrorType.UNKNOWN_EXCEPTION)

                    }
                    is SocketTimeoutException -> {
                        throw AppException(AppErrorType.SERVER_IS_NOT_AVAILABLE)
                    }
                    else -> {
                        throw AppException(AppErrorType.UNKNOWN_EXCEPTION)
                    }
                }
            }
        }
    }
}