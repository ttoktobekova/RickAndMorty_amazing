package com.example.paging.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.paging.R
import com.example.paging.databinding.ActivityDetailBinding
import com.example.paging.data.di.MainViewModel
import com.example.paging.utils.KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }
    private val viewModel: MainViewModel by viewModels()

    override  fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        displayData()
    }

    private  fun displayData() = with(binding) {
        val characterId = intent.getIntExtra(KEY.CHARACTER_ID, -1)
        if (characterId != -1) {
            observeCharacterDetails(characterId)
        }
    }

    private  fun observeCharacterDetails(characterId: Int) {
        viewModel.getCharactersDetails(characterId)
        viewModel.characterDetails.observe(this@DetailActivity) { character ->
            if (character != null) {
                with(binding) {
                    tvName.text = character.name
                    tvAliveAnimal.text = "${character.status} - ${character.species}"
                    tvFirstSeen.text = character.origin?.name
                    tvLocation.text = character.location?.name
                    imgCharacter.load(character.image)

                    when (character.status) {
                        "Alive" -> btnStatus.setImageResource(R.drawable.ic_circle)
                        "Dead" -> btnStatus.setImageResource(R.drawable.ic_circle_red)
                        else -> btnStatus.setImageResource(R.drawable.ic_circle_orange)
                    }
                }
            }
        }
    }

}