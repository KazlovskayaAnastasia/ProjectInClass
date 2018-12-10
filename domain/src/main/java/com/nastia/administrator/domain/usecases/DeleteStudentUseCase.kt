package com.nastia.administrator.domain.usecases

import executor.PostExecutorThread
import io.reactivex.Completable
import repositories.StudentRepository

class DeleteStudentUseCase(postExecutorThread: PostExecutorThread,
                           private val studentRepository: StudentRepository) : BaseUseCase(postExecutorThread) {

    fun delete(studentId: String): Completable {
        return studentRepository.delete(studentId)
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }
}