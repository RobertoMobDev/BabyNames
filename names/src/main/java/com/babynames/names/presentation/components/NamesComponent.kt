package com.babynames.names.presentation.components

import com.babynames.core.presentation.components.ApplicationComponent
import com.babynames.core.presentation.scopes.ActivityScope
import com.babynames.names.presentation.fragments.NamesFragment
import com.babynames.names.presentation.modules.NamesModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [NamesModule::class])
interface NamesComponent {

    fun inject(namesFragment: NamesFragment)

}