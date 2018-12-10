package repositories

import com.nastia.administrator.data.database.entity.dao.StudentDao
import com.nastia.administrator.data.database.entity.transformToDB
import entity.Student
import entity.StudentSearch
import entity.transformToDomain
import io.reactivex.Completable
import io.reactivex.Observable
import net.RestService
import javax.inject.Inject

class StudentRepositoryImpl @Inject constructor(val apiService: RestService,
                                                val studentDao: StudentDao) : StudentRepository { // singleton at fact

    private var lastTimeUpdate = 0L

    override fun get(): Observable<List<Student>> {

        return studentDao.getAll()
                .flatMap { studentDBList ->
                        apiService.getStudents()
                                .doOnNext {
                                    val list = it.map { it.transformToDB() }
                                    studentDao.deleteAll()
                                    studentDao.insert(list)
                                }
                                .map { list ->
                                    list.map { student -> student.transformToDomain() }
                                }
//                                .onErrorReturn {
////                                    if (studentDBList.isEmpty()) {
////                                        throw it
////                                    } else {
////                                        studentDBList
////                                                .map { it -> it.transformToDomain() }
////                                    }
////                                    Observable.just(studentDBList).map { list ->
////                                        list.map { student -> student.transformToDomain() }
////                                    }
//                                }
                    }
                }


//                .map { list ->
//                    list.map { student ->
//                        student.transformToDamain()
//                    }
//                }

//        return apiService.getStudents()
//                .map {
//                    it.map {
//                        it.transformToDomain()
//                    }
//                }

//        val list = listOf(
//                StudentDB(0, "Sergey", 25),
//                StudentDB(1, "Kate", 30),
//                StudentDB(2, "Alice", 20),
//                StudentDB(3, "Alex", 21),
//                StudentDB(4, "Ivan", 18))
//
//        return Observable.just(list)


    override fun search(search: StudentSearch): Observable<List<Student>> {

        val list = listOf<Student>(
                Student("0", "Kate", 30),
                Student("1", "Alice", 20))

        return Observable.just(list)
    }

    override fun update(student: Student): Completable {
        return Completable.complete()
    }

    override fun delete(studentId: String): Completable {
        return Completable.complete()
    }
}