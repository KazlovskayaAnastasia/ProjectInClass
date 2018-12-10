package presentation.screen.student

import android.Manifest
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import com.nastia.administrator.presentation.R
import com.nastia.administrator.presentation.databinding.ActivityStudentLandBinding
import presentation.base.BaseMvvmActivity
import presentation.utils.checkActivityResult
import presentation.utils.startCamera
import android.Manifest.permission
import com.tbruyelle.rxpermissions2.RxPermissions
import javax.inject.Inject


class StudentActivity : BaseMvvmActivity<StudentViewModel, ActivityStudentLandBinding, StudentRouter>() {

    @Inject
    override lateinit var viewModel: StudentViewModel

    @Inject
    override lateinit var router: StudentRouter

    override fun provideRouter(): StudentRouter {
        return router
    }

    override fun provideViewModel(): StudentViewModel {
        return viewModel
    }

    override fun provideLayoutId(): Int = R.layout.activity_student_main

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding.text_view.setText(" ")
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //application.filesDir //папка для файлов

        if (savedInstanceState == null) {
            router.goToStudentList()
        }

        val rxPermissions = RxPermissions(this)

        rxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe({ granted ->
                    if (granted) { // Always true pre-M
                        startCamera(this)
                    } else {

                    }
                })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val file = checkActivityResult(this, resultCode, resultCode, data)

        if (file != null) {

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onPause() {
        super.onPause()

    }
}
