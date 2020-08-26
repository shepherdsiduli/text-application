package com.shepherd.text.framework.di

import com.shepherd.text.framework.ListViewModel
import com.shepherd.text.framework.TextViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class])
interface ViewModelComponent {
    fun inject(textViewModel: TextViewModel)
    fun inject(listViewModel: ListViewModel)
}