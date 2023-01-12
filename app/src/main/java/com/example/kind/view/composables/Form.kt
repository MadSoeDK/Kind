package com.example.kind.view.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

/**
 * Author and source: author and source: https://www.section.io/engineering-education/jetpack-compose-forms/
 */
class FormState {
    var fields: List<KindTextField> = listOf()
        set(value) { field = value }

    fun validate(): Boolean {
        var valid = true
        fields.forEach {
            if (!it.validate()) {
                valid = false
            }
        }
        return valid
    }

    fun getData(): Map<String, String> = fields.map { it.name to it.text }.toMap()
}

@Composable
fun Form(state: FormState, fields: List<KindTextField>) {
    state.fields = fields

    Column {
        fields.forEach {
            it.Content()
        }
    }
}