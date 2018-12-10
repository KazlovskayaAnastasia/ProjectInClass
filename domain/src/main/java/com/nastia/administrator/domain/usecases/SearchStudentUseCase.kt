package com.nastia.administrator.domain.usecases

import entity.Student
import entity.StudentSearch
import executor.PostExecutorThread
import io.reactivex.Observable
import repositories.StudentRepository

class SearchStudentsUseCase(postExecutorThread: PostExecutorThread,
                            private val studentRepository: StudentRepository) : BaseUseCase(postExecutorThread) {

    fun search(searchStudent: StudentSearch): Observable<List<Student>> {
        return studentRepository.search(searchStudent)
                .observeOn(postExecutorThread)
                .subscribeOn(workExecutorThread)

    }
}