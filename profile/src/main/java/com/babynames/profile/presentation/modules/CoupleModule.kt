package com.babynames.profile.presentation.modules

import com.babynames.core.presentation.scopes.ActivityScope
import com.babynames.profile.data.repositoryimplementations.CoupleRepositoryImpl
import com.babynames.profile.data.services.api.CoupleService
import com.babynames.profile.domain.repositoryabstractions.CoupleRepository
import com.babynames.profile.presentation.presenters.abstractions.CouplePresenter
import com.babynames.profile.presentation.presenters.implementations.CouplePresenterImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class CoupleModule {

    @Provides
    @ActivityScope
    fun providesCoupleService(retrofit: Retrofit): CoupleService = retrofit.create(CoupleService::class.java)

    @Provides
    @ActivityScope
    fun providesCoupleRepository(coupleRepositoryImpl: CoupleRepositoryImpl): CoupleRepository = coupleRepositoryImpl

    @Provides
    @ActivityScope
    fun providesCouplePresenter(couplePresenterImpl: CouplePresenterImpl): CouplePresenter = couplePresenterImpl
}