package com.saksham.mybarcodescanner.di

import com.saksham.mybarcodescanner.data.repo.MainRepoImpl
import com.saksham.mybarcodescanner.domain.repo.MainRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindMainRepo(
        mainRepoImpl : MainRepoImpl
    ) : MainRepo


}