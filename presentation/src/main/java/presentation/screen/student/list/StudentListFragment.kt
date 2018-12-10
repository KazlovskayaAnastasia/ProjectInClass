package presentation.screen.student.list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.recyclerview.R.attr.layoutManager
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.nastia.administrator.presentation.R
import com.nastia.administrator.presentation.databinding.FragmentStudentListBinding
import io.reactivex.rxkotlin.subscribeBy
import presentation.base.BaseMvvmFragment
import presentation.screen.student.StudentRouter
import java.util.concurrent.TimeUnit

class StudentListFragment : BaseMvvmFragment<StudentListViewModel, FragmentStudentListBinding, StudentRouter>() {

    companion object {
        fun getInstance(): StudentListFragment {
            return StudentListFragment()
        }
    }

    override fun provideViewModel(): StudentListViewModel {
        return ViewModelProviders.of(this).get(StudentListViewModel::class.java)
    }

    override fun provideLayoutId(): Int {
        return R.layout.fragment_student_list
    }

    fun onViewCreate(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //binding.list_recycler_view.layoutManager.adapter = viewModel
        binding.listRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.listRecyclerView.setHasFixedSize(true)

        RxTextView.textChanges(binding.etSearch)
                .throttleFirst(500L, TimeUnit.MILLISECONDS)
                .subscribeBy({
                    //viewModel.search(it.toString())
                })
    }
}