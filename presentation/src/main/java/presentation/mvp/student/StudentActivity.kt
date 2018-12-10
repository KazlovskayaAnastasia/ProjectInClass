package presentation.mvp.student

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.nastia.administrator.presentation.R
import entity.Student
import kotlinx.android.synthetic.main.activity_student_mvp.*
import presentation.mvp.base.BaseMvpActivity

class StudentActivity : BaseMvpActivity<StudentPresenter, StudentRouter>(), StudentView {

    companion object {
        fun getIntent(context: Context) : Intent
        = Intent(context, StudentActivity.javaClass)
    }

    override fun providePresenter(): StudentPresenter
    = StudentPresenter(this)

    override fun provideRouter(): StudentRouter
        = StudentRouter(this)

    override fun provideLayoutId(): Int = R.layout.activity_student_mvp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        text_view.setOnClickListener {
            presenter.search()
        }
    }

    override fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun dismissProgressBar() {
        progress_bar.visibility = View.GONE
    }

    override fun showStudent(studentList: List<Student>) {
        //закинуть данные в адаптер
    }
}