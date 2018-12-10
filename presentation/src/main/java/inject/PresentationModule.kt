package inject

import dagger.Module
import dagger.android.ContributesAndroidInjector
import presentation.mvp.student.StudentActivity
import presentation.screen.student.StudentModule

@Module
abstract class PresentationModule {

    @ContributesAndroidInjector(modules = [StudentModule::class])
    abstract fun studentModule(): StudentActivity
}