package com.babynames.core.presentation.modules

import android.app.Application
import android.content.Context
import com.babynames.core.domain.executors.JobExecutor
import com.babynames.core.domain.executors.PostExecutionThread
import com.babynames.core.domain.executors.ThreadExecutor
import com.babynames.core.domain.executors.UIThread
import com.ia.mchaveza.kotlin_library.SharedPreferencesManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Application = this.application

    @Provides
    @Singleton
    fun providesContext(): Context = this.application.applicationContext

    @Provides
    @Singleton
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor = jobExecutor

    @Provides
    @Singleton
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread = uiThread

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferencesManager = SharedPreferencesManager(context)

    //TODO: provideDatabase
}