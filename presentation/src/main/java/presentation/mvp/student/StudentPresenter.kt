package presentation.mvp.student

import factories.UseCaseProvider
import io.reactivex.rxkotlin.subscribeBy
import presentation.mvp.base.BasePresenter

class StudentPresenter(view: StudentView) :
        BasePresenter<StudentRouter, StudentView>(view) {

    private val studentListUseCase = UseCaseProvider.provideGetStudentUseCase()

    override fun onResume(){
        super.onResume()
    }

    fun search(){
        view.showProgressBar()

        val disposable = studentListUseCase.get()
                .subscribeBy(
                        onNext = {
                            view.dismissProgressBar()
                            view.showStudent(it)
                        }
                )
        addToDisposible(disposable)
    }


}