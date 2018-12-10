package factories

import app.App
import com.nastia.administrator.data.database.entity.AppDatabase
import com.nastia.administrator.domain.usecases.GetStudentsUseCase
import com.nastia.administrator.domain.usecases.SearchStudentsUseCase
import executor.UIThread
import net.RestService
import repositories.StudentRepositoryImpl

object UseCaseProvider {

    //класс для реализации РЕСТ
    val restService = RestService("")

    //реализация интерфейса из домена, чтобы сказать в каком потоке выполнять
    val uiThread = UIThread()

    fun provideGetStudentUseCase():GetStudentsUseCase{

        val studentDao = AppDatabase.getInstance(App.instance.applicationContext).getStudentDao()



        val repository = StudentRepositoryImpl(restService, studentDao)

        val useCase = GetStudentsUseCase(uiThread, repository)
        return useCase
    }

    fun provideSearchStudentUseCase(): SearchStudentsUseCase {
        val studentDao = AppDatabase.getInstance(App.instance.applicationContext).getStudentDao()
        return SearchStudentsUseCase(uiThread, StudentRepositoryImpl(restService, studentDao))
    }
}