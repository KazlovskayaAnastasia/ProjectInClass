package com.nastia.administrator.data.database.entity

import entity.Student
import entity.StudentResponse

fun StudentResponse.transformToDomain() : StudentDB {
    return StudentDB(id = id, name = name, age = age)
}

fun StudentResponse.transformToDB() : StudentDB {
    return StudentDB(id = id, name = name, age = age)
}