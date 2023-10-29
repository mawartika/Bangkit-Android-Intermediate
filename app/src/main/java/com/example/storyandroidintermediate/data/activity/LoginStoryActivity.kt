package com.example.storyandroidintermediate.data.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.storyandroidintermediate.MainActivity
import com.example.storyandroidintermediate.R
import com.example.storyandroidintermediate.data.customview.MyEditTextStory
import com.example.storyandroidintermediate.data.viewmodel.LoginViewModelStory
import com.example.storyandroidintermediate.data.pref.StoryModel
import com.example.storyandroidintermediate.data.response.LoginResponse
import com.example.storyandroidintermediate.data.viewmodel.ViewModelFactoryStory
import com.example.storyandroidintermediate.databinding.ActivityLoginstoryBinding
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class LoginStoryActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModelStory> {
        ViewModelFactoryStory.getInstance(this)
    }
    private lateinit var binding: ActivityLoginstoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginstoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
        playAnimation()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        binding.loginButtonWelcomestory.setOnClickListener {
            login()
        }

        binding.emailEditTextSignupstory.addTextChangedListener { validateInputFields() }
        binding.passwordEditTextSignupstory.addTextChangedListener { validateInputFields() }
    }

    private fun validateInputFields() {
        val email = binding.emailEditTextSignupstory.text.toString()
        val password = binding.passwordEditTextSignupstory.text.toString()

        val isEmailValid = email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.isNotEmpty() && password.length >= 8

        binding.loginButtonWelcomestory.isEnabled = isEmailValid && isPasswordValid
    }

    private fun login() {
        val email = binding.emailEditTextSignupstory.text.toString()
        val password = binding.passwordEditTextSignupstory.text.toString()

        lifecycleScope.launch {
            try {
                val response = viewModel.getLogin(email, password)
                Log.d("LoginStoryActivity", "${response.message}")
            } catch (e: HttpException) {
                handleLoginError(e)
            }
        }
    }

    private fun handleLoginError(e: HttpException) {
        val jsonInString = e.response()?.errorBody()?.string()
        val errorBody = Gson().fromJson(jsonInString, LoginResponse::class.java)
    }



//sampai sini


private fun playAnimation() {
    ObjectAnimator.ofFloat(binding.imageViewWelcomestory, View.TRANSLATION_X, -30f, 30f).apply {
        duration = 6000
        repeatCount = ObjectAnimator.INFINITE
        repeatMode = ObjectAnimator.REVERSE
    }.start()

    val title = ObjectAnimator.ofFloat(binding.titleTextViewWelcomestory, View.ALPHA, 1f).setDuration(100)
    val message =
        ObjectAnimator.ofFloat(binding.messageTextViewLoginstory, View.ALPHA, 1f).setDuration(100)
    val emailTextView =
        ObjectAnimator.ofFloat(binding.emailTextViewSignupstory, View.ALPHA, 1f).setDuration(100)
    val emailEditTextLayout =
        ObjectAnimator.ofFloat(binding.emailEditTextLayoutSignupstory, View.ALPHA, 1f).setDuration(100)
    val passwordTextView =
        ObjectAnimator.ofFloat(binding.passwordTextViewSignupstory, View.ALPHA, 1f).setDuration(100)
    val passwordEditTextLayout =
        ObjectAnimator.ofFloat(binding.passwordEditTextLayoutSignupstory, View.ALPHA, 1f).setDuration(100)
    val login = ObjectAnimator.ofFloat(binding.loginButtonWelcomestory, View.ALPHA, 1f).setDuration(100)

    AnimatorSet().apply {
        playSequentially(
            title,
            message,
            emailTextView,
            emailEditTextLayout,
            passwordTextView,
            passwordEditTextLayout,
            login
        )
        startDelay = 100
    }.start()
}

}