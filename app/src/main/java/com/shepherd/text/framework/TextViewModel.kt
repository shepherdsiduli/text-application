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

class TextViewModel (application: Application) : AndroidViewModel(application){
    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule((getApplication())))
            .build()
            .inject(this)
    }

    val saved = MutableLiveData<Boolean>()

    val currentText = MutableLiveData<Text?>()

    fun saveText(text: Text){
        coroutineScope.launch {
            useCases.addText(text)
            saved.postValue(true)
        }
    }

    fun getText(id: Long){
        coroutineScope.launch {
            val text = useCases.getText(id)
            currentText.postValue(text)
        }
    }

    fun deleteText(text: Text){
        coroutineScope.launch {
            useCases.removeText(text)
            saved.postValue(true)
        }
    }
}