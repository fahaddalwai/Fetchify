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
        setContentView(view)

        // Binding ViewModel and Lifecycle Owner
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // Observe the state from the ViewModel to display list info
        viewModel.state.observe(this) { state ->
            // Directly display the entire state, which includes success data
            binding.textView.text = state.toString() // Display the state as it is
        }

        // Observe the loading state to show/hide the progress bar
        viewModel.showProgressBar.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        // Call to fetch all list info when the activity is created
        viewModel.fetchListInfo()
    }
}
