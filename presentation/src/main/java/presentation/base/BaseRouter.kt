package presentation.base

import android.support.v4.app.FragmentManager
import android.widget.Toast
import entity.AppErrorType
import entity.AppException

//реализуются переходы между экранами, используется для активити
abstract class BaseRouter<A : BaseActivity>(val activity: A) {

    fun goBack() {
        activity.onBackPressed()
    }

    fun showError(e: Throwable) {

        if (e is AppException){

            val message = when(e.errorType){
                AppErrorType.INTERNET_IS_NOT_AVAILABLE -> {
                    "INTERNET_IS_NOT_AVAILABLE"
                }
                AppErrorType.SERVER_IS_NOT_AVAILABLE -> {
                    "SERVER_IS_NOT_AVAILABLE"
                } else ->{
                    "UNKNOWN_EXCEPTION"
                }
            }
        }

        Toast.makeText(activity, "Error " + e.toString(), Toast.LENGTH_SHORT).show()
    }

    fun replaceFragment(fragmentManager: FragmentManager,
                        fragment: BaseFragment,
                        containerResId:Int,
                        addToBackStack: Boolean = false){
        var fragmentTransition = fragmentManager.beginTransaction()
        fragmentTransition.replace(containerResId, fragment, fragment::class.java.canonicalName)

        if(addToBackStack){
            fragmentTransition.addToBackStack(null)
        }

        fragmentTransition.commit()
    }
}