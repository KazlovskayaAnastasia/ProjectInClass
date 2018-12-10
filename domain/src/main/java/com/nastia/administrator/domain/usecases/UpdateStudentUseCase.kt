package com.nastia.administrator.domain.usecases

import entity.Student
import executor.PostExecutorThread
import io.reactivex.Completable
import repositories.StudentRepository

class UpdateStudentUseCase(postExecutorThread: PostExecutorThread,
                           private val studentRepository: StudentRepository) : BaseUseCase(postExecutorThread) {

    fun update(student: Student) : Completable{
        return studentRepository.update(student)
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)
    }
}
