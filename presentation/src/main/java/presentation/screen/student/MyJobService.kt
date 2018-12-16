package presentation.screen.student

import android.app.job.JobParameters
import android.app.job.JobService
import android.os.Build
import android.support.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class MyJobService: JobService() { //с api 23

    override fun onStartJob(params: JobParameters?): Boolean { // в UI потоке
        Thread.sleep(1000)

        return true

    }
    override fun onStopJob(params: JobParameters?): Boolean {
        return true
    }
}