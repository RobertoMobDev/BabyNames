package com.babynames.babynames.core.domain.executors

import java.util.concurrent.ThreadFactory

class JobThreadFactory : ThreadFactory {

    private var counter: Int = 0

    override fun newThread(r: Runnable?): Thread = Thread(r, "android_" + counter++)
}