package com.example.notes.di

import com.example.notes.data.remote.WorkManagerSyncScheduler
import com.example.notes.domain.remote.SyncScheduler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SyncModule {

    @Binds
    abstract fun bindSyncScheduler(
        impl: WorkManagerSyncScheduler
    ): SyncScheduler

}
