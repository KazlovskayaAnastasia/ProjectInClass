package presentation.screen.student.details

import android.databinding.ObservableBoolean
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import presentation.base.BaseViewModel
import presentation.screen.student.StudentRouter
import java.util.concurrent.TimeUnit

class StudentDetailsViewModel : BaseViewModel<StudentRouter>() {

    val clickSubject: PublishSubject<Boolean> = PublishSubject.create()

    override fun onResume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var studentId : String

    fun setStudentId(id: String) {

        if(studentId != null) return
        studentId = id

        //ToDo тут будет вызов UseCase, который возвращает студента по ID
    }
    val isProgressEnabled = ObservableBoolean(false)

    init {

        clickSubject
                .throttleFirst(500L, TimeUnit.MILLISECONDS)
                .subscribeBy {
                    //нажали кнопку
                }

    }
}