package com.nastia.administrator.data.database.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "student")
data class StudentDB(
        @PrimaryKey
        val id: String,

        val name: String,
        val age: Int
)