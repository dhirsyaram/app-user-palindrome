package com.kuliah.apppalindromeuser.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kuliah.apppalindromeuser.paging.RepositoryListUser

class ViewModelFactory(private val repository: RepositoryListUser) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ThirdViewModel::class.java)) {
            return ThirdViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}