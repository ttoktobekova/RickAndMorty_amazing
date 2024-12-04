package com.example.paging.data.di

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.paging.data.ApiService
import com.example.paging.data.models.Character
import com.example.paging.paging3.CharacterPagingSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val apiService: ApiService) {
    fun getCharacters(): Pager<Int, Character> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharacterPagingSource(apiService)
            }
        )

    }

    fun fetchCharacterDetails(id: Int): LiveData<Character> {
        return liveData {
            try {
                val character = apiService.getCharacterById(id)
                emit(character)
            } catch (e: Exception) {
                emit(Character())
            }
        }
    }
}
