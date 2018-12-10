package inject

import app.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import presentation.screen.student.list.StudentListViewModel

@Component(modules = [AndroidInjectionModule::class,
    AppModule::class,
    PresentationModule::class,
    RepositoryModule::class,
    DataModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun application(application: App)

        fun build():AppComponent
    }

    fun  inject(app: App)
    fun  inject(viewModel: StudentListViewModel)
}