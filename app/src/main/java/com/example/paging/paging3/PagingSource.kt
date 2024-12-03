package com.example.paging.paging3

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging.data.RetrofitInstance
import com.example.paging.data.models.Character
import java.lang.Exception

const val START_INDEX = 1

class CharacterPagingSource : PagingSource<Int, Character>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        return try {
            val currentPage = params.key ?: START_INDEX
            val response = RetrofitInstance.api.getCharacters(page = currentPage)
            val nextKey = if (response.info?.next == null) null else currentPage + 1
            val prevKey = if (response.info?.prev == null) null else currentPage - 1

            LoadResult.Page(
                data = response.characters ?: emptyList(),
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)

        }
    }

}