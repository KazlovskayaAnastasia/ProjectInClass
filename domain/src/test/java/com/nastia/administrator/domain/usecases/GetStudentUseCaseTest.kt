package com.nastia.administrator.domain.usecases

import entity.Student
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner
import repositories.StudentRepository
import org.mockito.Mockito.`when`

@RunWith(MockitoJUnitRunner::class)
class GetStudentUseCaseTest {

    @Mock
    lateinit var repository: StudentRepository

    @Test
    fun test() {

        val testScheduler = TestScheduler()

        `when`(repository.get()).thenReturn( //когда обращаемся к этому методу, возвращаем данные
                Observable.just(arrayListOf(
                        Student("1", "Student 1", 23),
                        Student("2", "Student 1", 23),
                        Student("3", "Student 1", 23)
                ))
        )

        val useCase = GetStudentsUseCase(repository)
        useCase.postExecutorThread = testScheduler
        useCase.workExecutorThread = testScheduler

        useCase
                .get()
                .subscribe{
                    assert(it.size == 3)
                }
    }
}