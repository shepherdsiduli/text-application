package com.shepherd.text.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.shepherd.core.data.Text
import com.shepherd.text.framework.di.ApplicationModule
import com.shepherd.text.framework.di.DaggerViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel(application: Application): AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule((getApplication())))
            .build()
            .inject(this)
    }

    val texts = MutableLiveData<List<Text>>()

    fun getTexts(){
        coroutineScope.launch {
            val textList = useCases.getAllTexts()
            texts.postValue(textList)
        }
    }
}