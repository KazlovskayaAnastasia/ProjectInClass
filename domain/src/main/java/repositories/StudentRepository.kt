package repositories

import entity.Student
import entity.StudentSearch
import io.reactivex.Completable
import io.reactivex.Observable

interface StudentRepository: BaseRepository {

    fun get(): Observable<List<Student>>

    fun search(search: StudentSearch):Observable<List<Student>>

    //ToDo реализовать возможность получить одного студента по ID
    //fun get(id:String):Observable<Student>

    fun update(student: Student): Completable

    fun delete(studentId: String): Completable
}