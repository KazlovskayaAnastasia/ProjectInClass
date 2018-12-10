package executor

import io.reactivex.Scheduler

interface PostExecutorThread {

    fun getScheduler(): Scheduler
}