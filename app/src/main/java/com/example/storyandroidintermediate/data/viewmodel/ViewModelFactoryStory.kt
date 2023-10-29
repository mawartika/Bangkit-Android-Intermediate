package com.example.storyandroidintermediate.data.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.storyandroidintermediate.data.remote.Injection
import com.example.storyandroidintermediate.data.repository.StoryRepository

class ViewModelFactoryStory (private val repository: StoryRepository) : ViewModelProvider.NewInstanceFactory (){
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModelStory::class.java) -> {
                MainViewModelStory(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModelStory::class.java) -> {
                LoginViewModelStory(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactoryStory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactoryStory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactoryStory::class.java) {
                    INSTANCE = ViewModelFactoryStory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactoryStory
        }
    }
}