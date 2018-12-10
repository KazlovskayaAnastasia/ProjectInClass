package com.nastia.administrator.domain.usecases

import executor.PostExecutorThread
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase() {

    lateinit var postExecutorThread: Scheduler
    lateinit var workExecutorThread: Scheduler

    constructor(postExecutorThread: Scheduler,
                workExecutorThread: Scheduler = Schedulers.io()) : this(){
        this.postExecutorThread = postExecutorThread
        this.workExecutorThread = workExecutorThread
    }

    constructor(postExecutorThread: PostExecutorThread) : this(postExecutorThread.getScheduler())


}