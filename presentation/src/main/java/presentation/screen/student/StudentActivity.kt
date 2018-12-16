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
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.util.Log
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

    @TargetApi(Build.VERSION_CODES.O)
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //application.filesDir //папка для файлов

        if (savedInstanceState == null) {
            router.goToStudentList()
        }

        val rxPermissions = RxPermissions(this)

        rxPermissions
                .request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe { granted ->
                    if (granted) { // Always true pre-M
                        startCamera(this)
                    } else {

                    }
                }

        val componentName = ComponentName(this, MyJobService::class.java)
        val jobInfo = JobInfo.Builder(123, componentName)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresBatteryNotLow(true)
                .setRequiresCharging(true)
                .build()

        val jobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler
        val result =  jobScheduler.schedule(jobInfo)
        if(result == JobScheduler.RESULT_SUCCESS){
            Log.e("AAA", "started")
        }

//        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//
//        val locationListener = object : LocationListener{
//
//            override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
//            }
//
//            override fun onProviderEnabled(provider: String?) {
//            }
//
//            override fun onProviderDisabled(provider: String?) {
//            }
//
//            override fun onLocationChanged(location: Location?) {
//            }
//        }
//
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
//                5000, 0f, locationListener)

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
