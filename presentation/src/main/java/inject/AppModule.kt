package inject

import android.content.Context
import app.App
import dagger.Binds
import dagger.Module
import dagger.Provides
import executor.PostExecutorThread
import executor.UIThread
import repositories.StudentRepository
import repositories.StudentRepositoryImpl

@Module
class AppModule {

    @Provides
    fun provideContext(app: App): Context = app.applicationContext

    @Provides
    fun providePostExecutorThread(): PostExecutorThread = UIThread()

    fun provideStudentRepository(repository: StudentRepositoryImpl)
            : StudentRepository
}