package com.example.fetchify.feature.presentation

import com.example.fetchify.feature.domain.model.ListInfo

data class ListInfoState(
    val listInfoItems: List<ListInfo> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null // Optional field for error messages
)