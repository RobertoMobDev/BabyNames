package com.babynames.core.domain.executors

import io.reactivex.Scheduler

interface PostExecutionThread {

    fun getScheduler(): Scheduler

}