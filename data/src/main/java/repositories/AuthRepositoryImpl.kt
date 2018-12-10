package repositories

import android.content.SharedPreferences
import com.nastia.administrator.data.database.entity.sharedprefs.AppSharedPrefs
import entity.Login
import entity.transformToDomain
import io.reactivex.Completable
import net.RestService

class AuthRepositoryImpl(private val sharedPrefs: AppSharedPrefs,
                         private val apiService: RestService) : AuthRepository {

    override fun login(login: Login): Completable {
        return Completable.fromObservable(
                apiService
                        .login(login.transformToDomain())
                        .doOnNext {
                            sharedPrefs.putToken(it.token)
                        })

    }

    override fun isLogin(): Boolean = sharedPrefs.getToken().isNotEmpty()

}