package com.example.storyandroidintermediate.data.repository

import com.example.storyandroidintermediate.data.pref.StoryModel
import com.example.storyandroidintermediate.data.pref.StoryPreference
import kotlinx.coroutines.flow.Flow


class StoryRepository private constructor(
    private val storyPreference: StoryPreference
){

    suspend fun saveSession(user: StoryModel){
        storyPreference.saveSession(user)
    }

    fun getSession(): Flow<StoryModel> {
        return storyPreference.getSession()
    }
    suspend fun logout(){
        storyPreference.logout()
    }
    companion object {
        @Volatile
        private var instance: StoryRepository? = null
        fun getInstance(
            userPreference: StoryPreference
        ): StoryRepository =
            instance ?: synchronized(this) {
                instance ?: StoryRepository(userPreference)
            }.also { instance = it }
    }

}