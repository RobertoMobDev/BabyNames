package com.babynames.login.presentation.modules

import com.babynames.core.presentation.scopes.ActivityScope
import com.babynames.login.data.repositoryimplementations.SignInRepositoryImpl
import com.babynames.login.data.services.api.SignInService
import com.babynames.login.domain.repositoryAbstractions.SignInRepository
import com.babynames.login.presentation.presenters.abstractions.SignInPresenter
import com.babynames.login.presentation.presenters.implementations.SignInPresenterImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class SignInModule {
    @Provides
    @ActivityScope
    fun provideSignInService(retrofit: Retrofit): SignInService = retrofit.create(SignInService::class.java)

    @Provides
    @ActivityScope
    fun providesSignInRepository(signInRepositoryImpl: SignInRepositoryImpl): SignInRepository = signInRepositoryImpl

    @Provides
    @ActivityScope
    fun provideSignInPresenter(signInPresenterImpl: SignInPresenterImpl): SignInPresenter = signInPresenterImpl
}