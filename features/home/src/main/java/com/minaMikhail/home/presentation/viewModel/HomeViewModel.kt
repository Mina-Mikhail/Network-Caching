package com.minaMikhail.home.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.minaMikhail.base.network.Resource
import com.minaMikhail.home.domain.model.HomeArticle
import com.minaMikhail.home.domain.useCase.GetNewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

    suspend fun getNews(): Flow<Resource<List<HomeArticle>?>> {
        return getNewsUseCase()
    }
}