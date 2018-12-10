package presentation.screen.student.details

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import com.nastia.administrator.presentation.R
import com.nastia.administrator.presentation.databinding.FragmentStudentDetailsBinding
import com.nastia.administrator.presentation.databinding.FragmentStudentListBinding
import presentation.base.BaseMvvmFragment
import presentation.screen.student.StudentRouter
import presentation.screen.student.list.StudentListViewModel

class StudentDetailsFragment : BaseMvvmFragment<StudentDetailsViewModel, FragmentStudentDetailsBinding, StudentRouter>() {

    companion object {

        private const val ID_EXTRA = "ID_EXTRA"

        fun getInstance(id:String): StudentDetailsFragment {
            val fragment = StudentDetailsFragment()
            val bundle = Bundle()
            bundle.putString(ID_EXTRA, id)
            fragment.arguments = bundle
            return StudentDetailsFragment()
        }
    }
    override fun provideViewModel(): StudentDetailsViewModel {
        return ViewModelProviders.of(this).get(StudentDetailsViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_student_list
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = arguments?.get(ID_EXTRA)
        if(id != null){
            //viewModel.setStudentId(id)
        }else{
            router?.goBack()
        }
    }
}