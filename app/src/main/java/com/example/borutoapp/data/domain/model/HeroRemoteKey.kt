package com.example.borutoapp.data.domain.model

import androidx.room.Entity
import com.example.borutoapp.util.Constants.HERO_REMOTE_KEY_TABLE


@Entity(tableName = HERO_REMOTE_KEY_TABLE)
data class HeroRemoteKey(
    val id: Int,
    val prevKey: Int?,
    val nextKey: Int?
)