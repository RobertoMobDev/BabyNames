package com.babynames.gender.presentation.components

import com.babynames.core.presentation.components.ApplicationComponent
import com.babynames.core.presentation.scopes.ActivityScope
import com.babynames.gender.presentation.GenderActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class])
interface GenderComponent {

    fun inject(genderActivity: GenderActivity)

}