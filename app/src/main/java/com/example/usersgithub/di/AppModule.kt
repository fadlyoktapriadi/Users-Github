package com.example.usersgithub.di

import com.example.usersgithub.domain.usecase.UserInteractor
import com.example.usersgithub.domain.usecase.UserUseCase
import com.example.usersgithub.ui.detail.DetailViewModel
import com.example.usersgithub.ui.favorite.FavoriteViewModel
import com.example.usersgithub.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val useCaseModule = module {
    factory<UserUseCase> { UserInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { FavoriteViewModel(get()) }
}