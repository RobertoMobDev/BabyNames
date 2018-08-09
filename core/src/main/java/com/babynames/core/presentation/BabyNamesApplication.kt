package com.babynames.core.presentation

import android.app.Application
import android.content.Context
import com.babynames.core.presentation.components.ApplicationComponent
import com.babynames.core.presentation.components.DaggerApplicationComponent
import com.babynames.core.presentation.modules.ApplicationModule
import com.babynames.core.presentation.modules.NetModule

class BabyNamesApplication : Application() {

    val applicationComponent: ApplicationComponent by lazy {
        DaggerApplicationComponent.builder()
                .netModule(NetModule("http://www.testing-reality.com"))
                .applicationModule(ApplicationModule(this))
                .build()
    }
}

fun Context.getApplicationComponent(): ApplicationComponent = (this.applicationContext as BabyNamesApplication).applicationComponent
