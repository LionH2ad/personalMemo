package com.lionhead.personalMemo.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String,
    val videoPath: String?,
    val weight: Int = 0,
    val reps: Int = 0,
    val sets: Int = 0
)
