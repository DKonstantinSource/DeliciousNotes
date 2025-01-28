package com.example.deliciousnotes.ui.newRecipe

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.deliciousnotes.Constants.Companion.CAMERA_REQUEST
import com.example.deliciousnotes.Constants.Companion.PICK_IMAGE_REQUEST
import com.example.deliciousnotes.R
import com.example.deliciousnotes.databinding.FragmentNewRecipeBinding
import com.example.deliciousnotes.domain.recipesList.model.Recipe
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream

class NewRecipeFragment : Fragment() {

    private var _binding: FragmentNewRecipeBinding? = null
    private val binding get() = _binding!!

    private val newRecipeViewModel: NewRecipeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewRecipeBinding.inflate(inflater, container, false)
        setupSpinner()
        setupFocusListener()

        binding.saveRecipe.setOnClickListener { saveRecipe() }

        newRecipeViewModel.imageUri.observe(viewLifecycleOwner) { uri ->
            uri?.let {
                binding.selectImageView.setImageURI(it)
                binding.selectImageView.setBackgroundResource(0)
            } ?: run {
                binding.selectImageView.setBackgroundResource(R.drawable.test_image)
            }
        }

        binding.selectImageView.setOnClickListener {
            showImageSourceDialog()
        }

        return binding.root
    }

    private fun setupSpinner() {
        val mealTimes = arrayOf(
            getString(R.string.breakfast),
            getString(R.string.lunch),
            getString(R.string.dinner)
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, mealTimes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.mealTime.adapter = adapter

        binding.mealTime.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                view?.let {
                    val selectedItem = parent.getItemAtPosition(position)
                    //TODO result on click
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                //TODO result on click
            }
        }
    }

    private fun setupFocusListener() {
        //TODO подумать над фокусом, как реализовать лаконичнее
        binding.ingredientInput.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.scrollView.post {
                    binding.scrollView.smoothScrollTo(0, view.top + view.height)
                }
            }
        }

        binding.recipeTitle.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.scrollView.post {
                    binding.scrollView.smoothScrollTo(0, view.top + view.height)
                }
            }
        }

        binding.technologyCooking.setOnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                binding.scrollView.post {
                    binding.scrollView.smoothScrollTo(0, view.top + view.height)
                }
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                PICK_IMAGE_REQUEST -> {
                    val imageUri = data?.data
                    newRecipeViewModel.setImageUri(imageUri)
                }
                CAMERA_REQUEST -> {
                    val imageBitmap = data?.extras?.get("data") as Bitmap
                    val uri = saveBitmapToFile(imageBitmap)
                    newRecipeViewModel.setImageUri(uri)
                }
            }
        }
    }

    private fun saveBitmapToFile(bitmap: Bitmap): Uri? {
        val file = File(context?.cacheDir, "temp_image.png")
        FileOutputStream(file).use { out ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
        }
        return Uri.fromFile(file)
    }

    private fun saveRecipe() {
        val title = binding.recipeTitle.text.toString().trim()
        val ingredients = binding.ingredientInput.text.toString().trim()
        val mealTime = binding.mealTime.selectedItem.toString()
        val cookingTechnology = binding.technologyCooking.text.toString().trim()
        val compound = binding.ingredientInput.text.toString().trim()
        val imageUri = newRecipeViewModel.imageUri.value

        val imageUriString = imageUri?.toString() ?: ""

        if (title.isNotEmpty() && ingredients.isNotEmpty()) {
            val recipe = Recipe(title, imageUriString, mealTime, cookingTechnology, compound)
            newRecipeViewModel.saveRecipe(recipe)
            clearFields()
        }
    }

    private fun clearFields() {
        binding.recipeTitle.text.clear()
        binding.ingredientInput.text.clear()
        binding.mealTime.setSelection(0)
        binding.technologyCooking.text.clear()
        newRecipeViewModel.setImageUri(null)
    }

    private fun showImageSourceDialog() {
        val options = arrayOf("Камера", "Галерея")
        AlertDialog.Builder(requireContext())
            .setTitle("Выберите источник изображения")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> openCamera()
                    1 -> openGallery()
                }
            }
            .show()
    }

    private fun openCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA_REQUEST)
    }

    private fun openGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}