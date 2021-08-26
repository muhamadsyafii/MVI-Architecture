package com.syafii.mvi.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.syafii.mvi.data.api.ApiClient
import com.syafii.mvi.data.api.ApiHelperImpl
import com.syafii.mvi.databinding.ActivityMainBinding
import com.syafii.mvi.ui.main.intent.MainIntent
import com.syafii.mvi.ui.main.viewmodel.MainViewModel
import com.syafii.mvi.ui.main.viewstate.MainState
import com.syafii.mvi.util.ViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private val adapter = MainAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupViewModel()
        observeViewModel()
        setupClicks()
    }

    private fun setupClicks() {
        binding.buttonFetchUser.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchUser)
            }
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            mainViewModel.state.collect {
                when(it){
                    is MainState.Idle -> {

                    }
                    is MainState.Loading -> {
                        binding.buttonFetchUser.visibility = View.GONE
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is MainState.Users -> {
                        binding.buttonFetchUser.visibility = View.GONE
                        binding.progressBar.visibility = View.GONE
                        binding.recyclerView.visibility = View.VISIBLE
                        adapter.setData(it.users)
                    }

                    is MainState.Error -> {
                        binding.buttonFetchUser.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.error, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(
                ApiHelperImpl(
                    ApiClient.service
                )
            )
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter
    }
}