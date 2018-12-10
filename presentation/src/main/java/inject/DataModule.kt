package inject

import android.content.Context
import app.App
import com.nastia.administrator.data.database.entity.AppDatabase
import com.nastia.administrator.data.database.entity.dao.StudentDao
import dagger.Module
import dagger.Provides
import net.RestService
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    fun provideStudentDao(appDatabase: AppDatabase): StudentDao = appDatabase.getStudentDao()

    @Provides
    fun provideAppDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    fun provideRestService(@Named(URL_INJECT_NAME_DEBAG) serverUrl: String): RestService
            = RestService(serverUrl)

    @Provides
    @Named(URL_INJECT_NAME_DEBAG)
    @Singleton
    fun provadeServerUrlDebag(): String = "https://cxsmdc"

    @Provides
    @Named(URL_INJECT_NAME_RELEASE)
    @Singleton
    fun provadeServerUrlRelease(): String = "https://cxsmdc"
}