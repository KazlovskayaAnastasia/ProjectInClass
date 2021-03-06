package presentation.mvp.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import presentation.base.BaseRouter

open class BasePresenter<R : BaseRouter<*>, V : BaseView>(val view: V) {

    protected var router : R? = null

    protected val compositeDisposable: CompositeDisposable by lazy {
        CompositeDisposable()
    }

    protected fun addToDisposible(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    fun addRouter(router: R?) {
       this.router = router
    }

    fun removeRouter(){
        this.router = null
    }

    public open fun onResume() {
    }

    public open fun onPause() {
    }

    public open fun onStart() {
    }

    public open fun onStop() {
    }

    public open fun onDestroy() {
        compositeDisposable.clear()
    }


}