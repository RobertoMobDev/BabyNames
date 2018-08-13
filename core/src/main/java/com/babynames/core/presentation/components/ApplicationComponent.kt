package com.babynames.core.presentation.components

import android.app.Application
import com.babynames.core.domain.executors.PostExecutionThread
import com.babynames.core.domain.executors.ThreadExecutor
import com.babynames.core.presentation.modules.ApplicationModule
import com.babynames.core.presentation.modules.NetModule
import com.ia.mchaveza.kotlin_library.SharedPreferencesManager
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, NetModule::class])
interface ApplicationComponent {

    fun context(): Application

    fun threadExecutor(): ThreadExecutor

    fun postExecutionThread(): PostExecutionThread

    //TODO: add DataBase

    fun retrofit(): Retrofit

    fun sharedPreferences(): SharedPreferencesManager
}