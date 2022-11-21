package com.example.kind.view.composables

import android.util.Patterns
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.kind.view.theme.paddingSize

/**
 * author and source: https://www.section.io/engineering-education/jetpack-compose-forms/
 */

private const val EMAIL_MESSAGE = "invalid email address"
private const val REQUIRED_MESSAGE = "this field is required"
private const val REGEX_MESSAGE = "value does not match the regex"

sealed interface Validator
open class Email(var message: String = EMAIL_MESSAGE): Validator
open class Required(var message: String = REQUIRED_MESSAGE): Validator
open class Regex(var message: String, var regex: String = REGEX_MESSAGE): Validator

class KindTextField (val name: String, val label: String = "",
                     val validators: List<Validator>) {
    var text: String by mutableStateOf("")
    var lbl: String by mutableStateOf(label)
    var hasError: Boolean by mutableStateOf(false)

    fun clear() { text = "" }

    private fun showError(error: String){
        hasError = true
        lbl = error
    }

    private fun hideError(){
        lbl = label
        hasError = false
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content(){
        TextField(
            value = text,
            isError = hasError,
            label = { Text(text = name) },
            modifier = Modifier.padding(MaterialTheme.paddingSize.s),
            onValueChange = { value ->
                hideError()
                text = value
            },
            singleLine = true,
        )
    }

    fun validate(): Boolean {
        return validators.map {
            when (it){
                is Email -> {
                    if (!Patterns.EMAIL_ADDRESS.matcher(text).matches()){
                        showError(it.message)
                        return@map false
                    }
                    true
                }
                is Required -> {
                    if (text.isEmpty()){
                        showError(it.message)
                        return@map  false
                    }
                    true
                }
                is Regex -> {
                    if (!it.regex.toRegex().containsMatchIn(text)){
                        showError(it.message)
                        return@map false
                    }
                    true
                }
            }
        }.all { it }
    }
}