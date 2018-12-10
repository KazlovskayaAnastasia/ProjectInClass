package entity

import com.google.gson.annotations.SerializedName

data class ErrorResponse(

        @SerializedName("massage")
        val massage: String = "",

        @SerializedName("errorCode")
        val errorCode: Int = 0) : DataEntity