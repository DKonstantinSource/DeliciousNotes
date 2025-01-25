package com.example.deliciousnotes.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deliciousnotes.R
import com.example.deliciousnotes.R.drawable.test_image
import com.example.deliciousnotes.domain.recipesList.model.Recipe

class RecipeAdapter(
    private val context: Context,
    private var recipeList: List<Recipe>
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var itemWidth: Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.recipeTitle.text = recipe.name
        holder.recipeImage.setImageResource(test_image)
        //TODO ссылки на image пока что нету !!

        val layoutParams = holder.itemView.layoutParams
        layoutParams.width = itemWidth
        holder.itemView.layoutParams = layoutParams
    }

    override fun getItemCount(): Int = recipeList.size


    fun updateRecipes(newRecipeList: List<Recipe>) {
        this.recipeList = newRecipeList
        notifyDataSetChanged()
    }

    fun onItemWidthSet(width: Int) {
        this.itemWidth = width
        notifyDataSetChanged()
    }

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val recipeImage: ImageView = itemView.findViewById(R.id.recipe_image)
        val recipeTitle: TextView = itemView.findViewById(R.id.name_recipe)
    }
}