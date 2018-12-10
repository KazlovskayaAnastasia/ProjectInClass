package presentation.screen.student

import android.view.View
import com.nastia.administrator.presentation.R
import presentation.base.BaseRouter
import presentation.screen.student.details.StudentDetailsFragment
import presentation.screen.student.list.StudentListFragment

class StudentRouter(activity: StudentActivity) // отвечает за переходы между экранами
    :BaseRouter<StudentActivity>(activity) {

    fun goToStudentList(){

        replaceFragment(activity.supportFragmentManager,
                StudentListFragment.getInstance(),
                R.id.container,
                false)
    }

    fun goToStudentDetails(id:String){

        val containerDetails =  activity.findViewById<View>(R.id.containerDetails)

        val containerResId : Int

        if (containerDetails == null){
            replaceFragment(activity.supportFragmentManager,
                    StudentDetailsFragment.getInstance(id),
                    R.id.container,
                    false)
        }else{
            replaceFragment(activity.supportFragmentManager,
                    StudentDetailsFragment.getInstance(id),
                    R.id.containerDetails,
                    false)
        }


    }
}