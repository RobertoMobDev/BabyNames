package com.babynames.babynames.presentation.components

import com.babynames.babynames.GenderActivity
import com.babynames.babynames.SplashActivity
import com.babynames.core.presentation.components.ApplicationComponent
import com.babynames.core.presentation.scopes.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface GenderComponent {

    fun inject(genderActivity: GenderActivity)
    fun inject(splashActivity: SplashActivity)

}