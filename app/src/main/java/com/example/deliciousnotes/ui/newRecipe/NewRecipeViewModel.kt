package com.example.deliciousnotes.ui.newRecipe


import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliciousnotes.domain.recipesList.api.ManageRecipeRepository
import com.example.deliciousnotes.domain.recipesList.model.Recipe
import kotlinx.coroutines.launch

class NewRecipeViewModel(private val repository: ManageRecipeRepository) : ViewModel() {

    private val _imageUri = MutableLiveData<Uri?>()
    val imageUri: LiveData<Uri?> get() = _imageUri

    fun saveRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.insertRecipe(recipe)
        }
    }

    fun setImageUri(uri: Uri?) {
        _imageUri.value = uri
    }
}