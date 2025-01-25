package com.example.deliciousnotes.mapper


import com.example.deliciousnotes.data.model.Recipe as DataRecipe
import com.example.deliciousnotes.domain.recipesList.model.Recipe as DomainRecipe

object RecipeMapper {
    fun map(dataRecipe: DataRecipe): DomainRecipe {
        return DomainRecipe(
            name = dataRecipe.name,
            image = dataRecipe.image,
            mealTime = dataRecipe.mealTime,
            cookingTechnology = dataRecipe.cookingTechnology,
            compound = dataRecipe.compound
        )
    }

    fun map(domainRecipe: DomainRecipe): DataRecipe {
        return DataRecipe(
            name = domainRecipe.name,
            image = domainRecipe.image,
            mealTime = domainRecipe.mealTime,
            cookingTechnology = domainRecipe.cookingTechnology,
            compound = domainRecipe.compound
        )
    }
}