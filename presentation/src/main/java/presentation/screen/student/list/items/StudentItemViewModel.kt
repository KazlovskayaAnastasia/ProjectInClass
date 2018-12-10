package presentation.screen.student.list.items

import android.databinding.ObservableField
import entity.Student
import presentation.mvp.base.recycler.BaseItemViewModel

class StudentItemViewModel: BaseItemViewModel<Student>() {

    val name = ObservableField<String>("")
    val age = ObservableField<String>("")

    override fun bindItem(item: Student, position: Int) {
        name.set(item.name)
        age.set(item.age.toString())
    }
}