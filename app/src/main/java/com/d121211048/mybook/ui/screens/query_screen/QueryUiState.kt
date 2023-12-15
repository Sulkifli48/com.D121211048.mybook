package com.d121211048.mybook.ui.screens.query_screen

import com.d121211048.mybook.model.Book

sealed interface QueryUiState {
    data class Success(val mybookList: List<Book>) : QueryUiState
    object Error : QueryUiState
    object Loading : QueryUiState
}