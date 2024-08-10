package com.kuliah.apppalindromeuser.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.kuliah.apppalindromeuser.data.response.DataItem
import com.kuliah.apppalindromeuser.paging.RepositoryListUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class ThirdViewModel(userRepository: RepositoryListUser) : ViewModel() {
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val userList: Flow<PagingData<DataItem>> = userRepository.getUsers()
        .onStart { _isLoading.value = true }
        .onCompletion { _isLoading.value = false }
        .catch { e ->
            _isLoading.value = false
            Log.e("Error[ThirdViewModel]", e.message.toString())
        }
}
