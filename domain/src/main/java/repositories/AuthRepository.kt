package repositories

import entity.Login
import io.reactivex.Completable

interface AuthRepository : BaseRepository {

    fun login(login : Login) : Completable

    fun isLogin(): Boolean

    //fun getToken(): Observable<Token>
}