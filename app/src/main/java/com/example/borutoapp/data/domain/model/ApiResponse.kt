package com.example.borutoapp.data.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nexPage: Int? = null,
    val heroes: List<Hero> = emptyList()
)
