package com.example.paging.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.example.paging.data.models.Character
import com.example.paging.databinding.ItemCharacterBinding


class CharacterAdapter :
    PagingDataAdapter<Character, CharacterAdapter.CharacterViewHolder>(DiffUtilCallBack()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        if (character != null) {
            holder.bind(character)
        }

    }

    class CharacterViewHolder(private val binding: ItemCharacterBinding) :
        ViewHolder(binding.root) {
        fun bind(character: Character) = with(binding) {
            imgCharacter.load(character.image)
            tvName.text = character.name
            tvStatus.text = character.status
        }

    }

    class DiffUtilCallBack : DiffUtil.ItemCallback<Character>() {
        override fun areItemsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Character, newItem: Character): Boolean {
            return oldItem == newItem
        }

    }


}