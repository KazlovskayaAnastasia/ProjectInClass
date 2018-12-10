package presentation.base

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import com.android.databinding.library.baseAdapters.BR

abstract class BaseMvvmActivity<VM : BaseViewModel<R>,
        B : ViewDataBinding,
        R: BaseRouter<*>> : BaseActivity() {

    protected open lateinit var viewModel: VM
    protected lateinit var binding: B //our view
    open lateinit var router: R

    abstract fun provideViewModel() : VM

    abstract fun provideRouter() : R

    abstract fun provideLayoutId() : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = provideViewModel()
        binding = DataBindingUtil.setContentView(this, provideLayoutId())
        binding.setVariable(BR.StudentViewModel, viewModel) //add viewModel to xml

        router = provideRouter()
    }

    override fun onResume() {
        super.onResume()
        viewModel.addRouter(router)
        viewModel.onResume()
    }

    override fun onPause() {
        super.onPause()
        viewModel.removeRouter()
        viewModel.removeDisposable()
    }
}