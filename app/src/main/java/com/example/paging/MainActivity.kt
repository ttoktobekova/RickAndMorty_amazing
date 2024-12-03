package com.example.paging

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.paging.data.CharacterAdapter
import com.example.paging.databinding.ActivityMainBinding
import com.example.paging.ui.MainViewModel

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel>()
    private val noteAdapter = CharacterAdapter()
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvCharacter.adapter = noteAdapter

        viewModel.getCharacters().observe(this) { data ->
            noteAdapter.submitData(lifecycle,data)
        }
    }
}