package com.example.mediaupload.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Event(
    val id: Int? = null,
    val user_id: String,
    val title: String,
    val description: String,
    val date: String
)
