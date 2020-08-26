package com.shepherd.text.framework.di

import com.shepherd.core.repository.TextRepository
import com.shepherd.core.usecase.*
import com.shepherd.text.framework.UseCases
import dagger.Module
import dagger.Provides

@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: TextRepository) = UseCases(
        AddText(repository),
        GetAllTexts(repository),
        GetText(repository),
        RemoveText(repository)
    )
}