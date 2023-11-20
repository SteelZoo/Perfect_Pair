package com.olduo.last_dance.modules

import com.olduo.last_dance.data.repositoryImpl.UserRepositoryImpl
import com.olduo.last_dance.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindUserRepo(userRepositoryImpl: UserRepositoryImpl): UserRepository

}