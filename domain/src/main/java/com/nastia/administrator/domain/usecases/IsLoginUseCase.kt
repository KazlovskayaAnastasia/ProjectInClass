package com.nastia.administrator.domain.usecases

import io.reactivex.Single
import repositories.AuthRepository

class IsLoginUseCase(private val authRepository: AuthRepository) : BaseUseCase() {

    fun isLogin(): Single<Boolean> = Single.just(authRepository.isLogin())

}