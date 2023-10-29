package com.example.storyandroidintermediate.data.remote

import android.content.Context
import com.example.storyandroidintermediate.data.pref.StoryPreference
import com.example.storyandroidintermediate.data.pref.dataStore
import com.example.storyandroidintermediate.data.repository.StoryRepository

object Injection {
    fun provideRepository(context: Context): StoryRepository {
        val pref = StoryPreference.getInstance(context.dataStore)
        return StoryRepository.getInstance(pref)
    }
}