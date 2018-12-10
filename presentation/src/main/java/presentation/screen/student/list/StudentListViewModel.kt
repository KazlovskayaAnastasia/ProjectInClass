package presentation.screen.student.list

import android.databinding.ObservableBoolean
import android.support.v7.widget.RecyclerView
import app.App
import com.nastia.administrator.domain.usecases.GetStudentsUseCase
import com.nastia.administrator.domain.usecases.SearchStudentsUseCase
import factories.UseCaseProvider
import io.reactivex.rxkotlin.subscribeBy
import presentation.base.BaseViewModel
import presentation.screen.student.StudentRouter
import java.util.*
import javax.inject.Inject

class StudentListViewModel : BaseViewModel<StudentRouter>() {
    override fun onResume() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //поменять на свой адаптер
    val adapter: RecyclerView.Adapter<*>? = null

    val isProgressEnabled = ObservableBoolean(false)

    @Inject
    lateinit var studentListUseCase : GetStudentsUseCase

    @Inject
    lateinit var searchStudentListUseCase : SearchStudentsUseCase

    init {

        App.appComponent.inject(this) //проинициализирует все значения где @Inject

        //ToDo При клике на юзера вызывать router.goToStudentDetails()
        //adapter.setListener

        isProgressEnabled.set(true)
        val disposable = studentListUseCase.get().subscribeBy(
                onNext = {
                    //ToDo передать данные в адаптер
                    isProgressEnabled.set(false)
                },
                onError = {
                    router?.showError(it)
                    isProgressEnabled.set(false)
                }
        )
        addToDisposible(disposable)
    }
}