package com.babynames.login.presentation.components

import com.babynames.core.presentation.components.ApplicationComponent
import com.babynames.core.presentation.scopes.ActivityScope
import com.babynames.login.presentation.WelcomeActivity
import com.babynames.login.presentation.modules.SignInModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [SignInModule::class])
interface SignInComponent {

    fun inject(welcomeActivity: WelcomeActivity)

}