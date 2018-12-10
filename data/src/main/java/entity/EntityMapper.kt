package entity

fun StudentResponse.transformToDomain() : Student{
    return Student(id = id, name = name, age = age)
}

fun Login.transformToDomain(): LoginRequest{
    return LoginRequest(login = login, password = password)
}