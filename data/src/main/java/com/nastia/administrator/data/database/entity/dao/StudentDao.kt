package com.nastia.administrator.data.database.entity.dao

import android.arch.persistence.room.*
import com.nastia.administrator.data.database.entity.StudentDB
import io.reactivex.Observable

@Dao
interface StudentDao {

    @Insert
    fun insert(vararg student: List<StudentDB>)

    @Update
    fun update(student: StudentDB)

    @Delete
    fun delete(student: StudentDB) // удалить одного студента

    @Query("DELETE FROM student")
    fun deleteAll() // удалить всех студентов

    @Query("DELETE FROM student WHERE id = :id")
    fun deleteById(id: String) // удалить по id

    @Query("SELECT * FROM student ORDER BY name")
    fun getAll(): Observable<List<StudentDB>>

    @Query("SELECT * FROM student WHERE id = :id LIMIT 1")
    fun getById(id: String): Observable<List<StudentDB>>
}