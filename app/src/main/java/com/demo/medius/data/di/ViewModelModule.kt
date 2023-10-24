package com.demo.medius.data.di

import com.demo.medius.data.viewmodels.OTPViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { OTPViewModel(get()) }
}
