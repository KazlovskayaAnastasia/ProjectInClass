package presentation.mvp.student

import entity.Student
import presentation.mvp.base.BaseView

interface StudentView : BaseView {

    fun showProgressBar()
    fun dismissProgressBar()
    fun showStudent(studentList: List<Student>)
}