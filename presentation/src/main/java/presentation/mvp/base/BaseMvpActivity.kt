package presentation.mvp.base

import android.os.Bundle
import presentation.base.BaseActivity
import presentation.base.BaseRouter

abstract class BaseMvpActivity<P : BasePresenter<R, *>,
        R: BaseRouter<*>> : BaseActivity() {

    lateinit var presenter : P
    lateinit var router: R

    abstract fun providePresenter() : P
    abstract fun provideRouter() : R
    abstract fun provideLayoutId() : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(provideLayoutId())

        presenter = providePresenter()
        router = provideRouter()
    }

    override fun onResume() {
        super.onResume()
        presenter.addRouter(router)
        presenter.onPause()
    }

    override fun onPause() {
        super.onPause()
        presenter.removeRouter()
        presenter.onPause()
    }

    override fun onStart() {
        super.onStart()
        presenter.onStart()
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}