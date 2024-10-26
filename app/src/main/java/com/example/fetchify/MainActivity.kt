package com.example.fetchify

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.example.fetchify.databinding.ActivityMainBinding
import com.example.fetchify.feature.presentation.ListInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ListInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(R.layout.activity_main)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Observe the state from the ViewModel to display word info
        viewModel.state.observe(this) {
            binding.textView.text = viewModel.state.toString() // Display word information
        }

        // Observe the loading state to show/hide the progress bar
        viewModel.showProgressBar.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        // Call to fetch all word info when the activity is created
        viewModel.fetchListInfo()

    }
}