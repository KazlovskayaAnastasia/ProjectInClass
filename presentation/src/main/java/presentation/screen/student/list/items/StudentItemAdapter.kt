package presentation.screen.student.list.items

import android.view.ViewGroup
import entity.Student
import presentation.mvp.base.recycler.BaseRecyclerAdapter

class StudentItemAdapter : BaseRecyclerAdapter<Student, StudentItemViewModel>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int): StudentItemViewHolder {
        return StudentItemViewHolder.create(viewGroup, StudentItemViewModel())

    }
}