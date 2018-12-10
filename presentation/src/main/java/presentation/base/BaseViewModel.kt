package presentation.base

import android.arch.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel<R:BaseRouter<*>> : ViewModel() {

    protected var router: R? = null
    protected val compositeDisposable : CompositeDisposable by lazy {
        CompositeDisposable()
    }

    fun addRouter(router: R?) {
        this.router = router
    }

    fun removeRouter(){
        this.router = null
    }

    fun removeDisposable(){
        compositeDisposable.clear()
    }

    protected fun addToDisposible(disposable: Disposable){
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    abstract  fun onResume()
}