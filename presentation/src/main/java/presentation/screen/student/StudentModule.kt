package presentation.screen.student

import dagger.Module
import dagger.android.ContributesAndroidInjector
import presentation.screen.student.details.StudentDetailsFragment
import presentation.screen.student.details.StudentDetailsModule
import presentation.screen.student.list.StudentListFragment
import presentation.screen.student.list.StudentListModule

@Module
abstract class StudentModule {

    @Module
    companion object {
        //стандартные провайдеры
    }

    @ContributesAndroidInjector(modules = [StudentListModule::class])
    abstract fun studentListModule(): StudentListFragment

    @ContributesAndroidInjector(modules = [StudentDetailsModule::class])
    abstract fun studentDetailsModule(): StudentDetailsFragment
}