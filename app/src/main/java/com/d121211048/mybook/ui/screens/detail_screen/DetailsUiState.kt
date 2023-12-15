package com.d121211048.mybook.ui.screens.detail_screen

import com.d121211048.mybook.model.Book

sealed interface DetailsUiState {
    data class Success(val bookItem: Book) : DetailsUiState
    object Error : DetailsUiState
    object Loading : DetailsUiState
}