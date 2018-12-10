package presentation.screen.student.list.items

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nastia.administrator.presentation.databinding.ItemStudentBinding
import entity.Student
import presentation.mvp.base.recycler.BaseViewHolder

class StudentItemViewHolder(binding: ItemStudentBinding,
                            viewModel: StudentItemViewModel)
    : BaseViewHolder<Student, StudentItemViewModel, ItemStudentBinding>(binding, viewModel) {

    companion object {
        fun create(parent: ViewGroup,
                   viewModel: StudentItemViewModel):StudentItemViewHolder{

            val binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context),
                    parent, false)
            return StudentItemViewHolder(binding, viewModel)
        }
    }
}