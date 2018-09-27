package com.babynames.names.presentation.modules

import com.babynames.core.presentation.scopes.ActivityScope
import com.babynames.names.data.datasourceimplementations.NamesApiDataSourceImpl
import com.babynames.names.data.repositoryimplementations.NamesRepositoryImpl
import com.babynames.names.data.services.api.NamesService
import com.babynames.names.domain.datasourceAbstraction.NamesDataSource
import com.babynames.names.domain.repositoryAbstractions.NamesRepository
import com.babynames.names.presentation.presenters.abstractions.NamesPresenter
import com.babynames.names.presentation.presenters.implementations.NamesPresenterImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class NamesModule {

    @Provides
    @ActivityScope
    fun providesNamesService(retrofit: Retrofit): NamesService = retrofit.create(NamesService::class.java)

    @Provides
    @ActivityScope
    fun providesNamesDataSource(namesService: NamesService): NamesDataSource = NamesApiDataSourceImpl(namesService)

    @Provides
    @ActivityScope
    fun providesNamesRepository(namesRepositoryImpl: NamesRepositoryImpl): NamesRepository = namesRepositoryImpl


    @Provides
    @ActivityScope
    fun providesNamesPresenter(namesPresenterImpl: NamesPresenterImpl): NamesPresenter = namesPresenterImpl
}