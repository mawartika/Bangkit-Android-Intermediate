package com.example.storyandroidintermediate.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.storyandroidintermediate.data.repository.StoryRepository
import com.example.storyandroidintermediate.data.pref.StoryModel
import com.example.storyandroidintermediate.data.remote.ApiConfig
import com.example.storyandroidintermediate.data.response.LoginResponse
import com.example.storyandroidintermediate.data.response.RegisterResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModelStory(private val repository: StoryRepository) : ViewModel() {
    fun saveSession(user: StoryModel) {
        viewModelScope.launch {
            repository.saveSession(user)
        }
    }
    private val _isLogin = MutableLiveData<LoginResponse>()
    val login: LiveData<LoginResponse> = _isLogin

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        const val TAG = "LoginViewModelStory"
    }


    suspend fun getLogin(email: String, password: String) {
        _isLoading.value = true
        val client = ApiConfig.getApiService().login(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _isLogin.value = response.body()
                } else {
                    Log.e(LoginViewModelStory.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(LoginViewModelStory.TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }
}