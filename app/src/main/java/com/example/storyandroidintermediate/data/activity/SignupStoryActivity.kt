package com.example.storyandroidintermediate.data.activity

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.storyandroidintermediate.databinding.ActivitySignupstoryBinding

class SignupStoryActivity : AppCompatActivity(){
private lateinit var binding: ActivitySignupstoryBinding

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivitySignupstoryBinding.inflate(layoutInflater)
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
    binding.signupButtonWelcomestory.setOnClickListener {

        binding.emailEditTextSignupstory.addTextChangedListener { validateInputFields() }
        binding.passwordEditTextSignupstory.addTextChangedListener { validateInputFields() }

        val email = binding.emailEditTextSignupstory.text.toString()
        val password = binding.passwordEditTextSignupstory.text.toString()

        val isEmailValid = email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.isNotEmpty() && password.length >= 8

        binding.signupButtonWelcomestory.isEnabled = isEmailValid && isPasswordValid
    }
}

    private fun validateInputFields() {
        val email = binding.emailEditTextSignupstory.text.toString()
        val password = binding.passwordEditTextSignupstory.text.toString()

        val isEmailValid = email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.isNotEmpty() && password.length >= 8

        binding.signupButtonWelcomestory.isEnabled = isEmailValid && isPasswordValid
    }

    //sampai sini


private fun playAnimation() {
    ObjectAnimator.ofFloat(binding.imageViewWelcomestory, View.TRANSLATION_X, -30f, 30f).apply {
        duration = 6000
        repeatCount = ObjectAnimator.INFINITE
        repeatMode = ObjectAnimator.REVERSE
    }.start()

    val title = ObjectAnimator.ofFloat(binding.titleTextViewWelcomestory, View.ALPHA, 1f).setDuration(100)
    val nameTextView =
        ObjectAnimator.ofFloat(binding.nameTextViewSignupstory, View.ALPHA, 1f).setDuration(100)
    val nameEditTextLayout =
        ObjectAnimator.ofFloat(binding.nameEditTextLayoutSignupstory, View.ALPHA, 1f).setDuration(100)
    val emailTextView =
        ObjectAnimator.ofFloat(binding.emailTextViewSignupstory, View.ALPHA, 1f).setDuration(100)
    val emailEditTextLayout =
        ObjectAnimator.ofFloat(binding.emailEditTextLayoutSignupstory, View.ALPHA, 1f).setDuration(100)
    val passwordTextView =
        ObjectAnimator.ofFloat(binding.passwordTextViewSignupstory, View.ALPHA, 1f).setDuration(100)
    val passwordEditTextLayout =
        ObjectAnimator.ofFloat(binding.passwordEditTextLayoutSignupstory, View.ALPHA, 1f).setDuration(100)
    val signup = ObjectAnimator.ofFloat(binding.signupButtonWelcomestory, View.ALPHA, 1f).setDuration(100)


    AnimatorSet().apply {
        playSequentially(
            title,
            nameTextView,
            nameEditTextLayout,
            emailTextView,
            emailEditTextLayout,
            passwordTextView,
            passwordEditTextLayout,
            signup
        )
        startDelay = 100
    }.start()
}
}