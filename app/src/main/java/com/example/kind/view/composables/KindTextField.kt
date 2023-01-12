package com.example.kind.view.composables

import android.util.Patterns
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * author and source: https://www.section.io/engineering-education/jetpack-compose-forms/
 */

private const val EMAIL_MESSAGE = "Invalid email address"
private const val REQUIRED_MESSAGE = "This field is required"
private const val REGEX_MESSAGE = "Value does not match the regex"
private const val PASSWORD_MESSAGE = "The password must have minimum 6 characters"

sealed interface Validator
open class Email(var message: String = EMAIL_MESSAGE): Validator
open class Required(var message: String = REQUIRED_MESSAGE): Validator
open class Password(var message: String = PASSWORD_MESSAGE): Validator
open class Regex(var message: String, var regex: String = REGEX_MESSAGE): Validator

class KindTextField (val name: String, val label: String = "", val validators: List<Validator>) {
    var text: String by mutableStateOf("")
    var lbl: String by mutableStateOf(label)
    var errorText: String by mutableStateOf("")
    var hasError: Boolean by mutableStateOf(false)

    fun clear() {
        text = ""
        errorText = ""
    }

    fun showError(error: String) {
        hasError = true
        errorText = error
    }

    private fun hideError() {
        hasError = false
        errorText = ""
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Content() {
        TextField(
            value = text,
            isError = hasError,
            label = { Text(text = lbl) },
            modifier = Modifier.padding(10.dp),
            onValueChange = { value ->
                hideError()
                text = value
            },
            singleLine = true,
            supportingText = { if (hasError) Text(text = errorText) }
        )
    }

    fun validate(): Boolean {
        return validators.map {
            when (it) {
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
                is Password -> {
                    if (text.length <= 5) {
                        showError(it.message)
                        return@map false
                    }
                    true
                }
            }
        }.all { it }
    }
}