package com.example.paging.data.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.paging.data.models.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: CharacterRepository) : ViewModel() {
    val characters: LiveData<PagingData<Character>> = repository.getCharacters().liveData

    private val _characterDetails = MutableLiveData<Character>()
    val characterDetails: LiveData<Character> get() = _characterDetails

     fun getCharactersDetails(id: Int) {
         repository.fetchCharacterDetails(id).observeForever{
             _characterDetails.value = it
         }
    }
}