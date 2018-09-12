package com.babynames.profile.presentation.components

import com.babynames.core.presentation.components.ApplicationComponent
import com.babynames.core.presentation.scopes.ActivityScope
import com.babynames.profile.presentation.activities.ScanQRActivity
import com.babynames.profile.presentation.modules.CoupleModule
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [CoupleModule::class])
interface CoupleComponent {

    fun inject(scanQRActivity: ScanQRActivity)

}