package com.example.deliciousnotes.domain.recipesList.model

import java.io.Serializable

data class Recipe(
    val name: String,
    val image: String,
    val mealTime: String,
    val cookingTechnology: String,
    val compound: String,
) : Serializable {
    fun getCoverArtwork() = image
}