package com.example.storyandroidintermediate.data.pref

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class StoryModel(
    val email: String,
    val token: String,
    val isLogin: Boolean = false
): Parcelable