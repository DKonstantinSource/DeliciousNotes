package com.example.deliciousnotes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "recipes")
data class Recipe(
    val name: String,
    val image: String,
    val mealTime: String,
    val cookingTechnology: String,
    val compound: String,
    @PrimaryKey(autoGenerate = true) val id: Int = 0
) : Serializable {
    fun getCoverArtwork() = image
}