package com.shepherd.text.framework.di

import android.app.Application
import com.shepherd.core.repository.TextRepository
import com.shepherd.text.framework.RoomTextDataSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(app: Application) = TextRepository(RoomTextDataSource(app))
}