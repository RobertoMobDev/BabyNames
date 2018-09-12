package com.babynames.profile.presentation.modules

import com.babynames.core.presentation.scopes.ActivityScope
import com.babynames.profile.data.repositoryimplementations.CoupleRepositoryImpl
import com.babynames.profile.data.services.api.CoupleService
import com.babynames.profile.domain.repositoryabstractions.CoupleRepository
import dagger.Provides
import retrofit2.Retrofit

class CoupleModule {

    @Provides
    @ActivityScope
    fun providesCoupleService(retrofit: Retrofit): CoupleService = retrofit.create(CoupleService::class.java)

    @Provides
    @ActivityScope
    fun providesCoupleRepository(coupleRepositoryImpl: CoupleRepositoryImpl): CoupleRepository = coupleRepositoryImpl
}