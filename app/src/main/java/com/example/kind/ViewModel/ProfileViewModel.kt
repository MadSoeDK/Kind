package com.example.kind.ViewModel

import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    fun getButtonText(): String {
        return "Button text";
    }
}