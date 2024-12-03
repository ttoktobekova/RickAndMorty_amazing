package com.example.paging.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.paging.data.models.Character
import com.example.paging.paging3.CharacterPagingSource

class MainViewModel():ViewModel() {
    fun getCharacters():LiveData<PagingData<Character>> {
        return  Pager(
            config = PagingConfig(
                pageSize = 20,
                prefetchDistance = 30,
                enablePlaceholders = false
            ), pagingSourceFactory = {CharacterPagingSource()}
        ).liveData
    }
}