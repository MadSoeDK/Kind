package com.example.kind.view.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignupViewModel : ViewModel() {
    var steps = mutableStateOf(0)
}