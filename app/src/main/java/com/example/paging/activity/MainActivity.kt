package com.example.paging.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.paging.adapter.CharacterAdapter
import com.example.paging.data.models.Character
import com.example.paging.databinding.ActivityMainBinding
import com.example.paging.data.di.MainViewModel
import com.example.paging.utils.KEY
import com.example.paging.utils.OnClick
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnClick {
    private val viewModel: MainViewModel by viewModels()
    private val noteAdapter = CharacterAdapter(this)
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
    }


    private fun setupRecyclerView() = with(binding) {
        rvCharacter.adapter = noteAdapter

    }

    private fun observeViewModel() = with(binding) {
        viewModel.characters.observe(this@MainActivity) { data ->
            noteAdapter.submitData(lifecycle, data)
        }
    }

    override fun onClick(character: Character) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
            putExtra(KEY.CHARACTER_ID, character.id)
        }
        startActivity(intent)
    }

}