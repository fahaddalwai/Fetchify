package com.example.fetchify

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.fetchify.databinding.ActivityMainBinding
import com.example.fetchify.feature.presentation.ListInfoAdapter
import com.example.fetchify.feature.presentation.ListInfoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: ListInfoViewModel by viewModels()
    private val adapter = ListInfoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the adapter to the RecyclerView
        binding.recyclerView.adapter = adapter

        // Observe loading state to show/hide the progress bar
        viewModel.showProgressBar.observe(this) { isLoading ->
            binding.progressBar.isVisible = isLoading
        }

        // Observe the list data and submit it to the adapter
        viewModel.state.observe(this, Observer { state ->
            Log.e("Someone submitted", state.toString())
            adapter.submitList(state.listInfoItems)
        })

        // Fetch the list info on activity creation
        viewModel.fetchListInfo()
    }
}
