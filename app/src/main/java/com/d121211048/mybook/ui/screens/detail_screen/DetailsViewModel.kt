package com.d121211048.mybook.ui.screens.detail_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.d121211048.mybook.MybookApplication
import com.d121211048.mybook.data.MybookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class DetailsViewModel(
    private val mybookRepository: MybookRepository
): ViewModel() {
    private val _uiStateDetail = MutableStateFlow<DetailsUiState>(DetailsUiState.Loading)
    val uiStateDetail = _uiStateDetail.asStateFlow()


    fun getBook(id: String) {
        viewModelScope.launch {
            _uiStateDetail.value = try {
                // Notes: List<Book>? NULLABLE
                val book = mybookRepository.getBook(id)
                if (book == null) {
                    DetailsUiState.Error
                } else{
                    DetailsUiState.Success(book)
                }
            } catch (e: IOException) {
                DetailsUiState.Error
            } catch (e: HttpException) {
                DetailsUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MybookApplication)
                val mybookRepository = application.container.mybookRepository
                DetailsViewModel(mybookRepository = mybookRepository)
            }
        }
    }
}