package com.example.paging.utils

import com.example.paging.data.models.Character

interface OnClick {
    fun onClick(character: Character)
}