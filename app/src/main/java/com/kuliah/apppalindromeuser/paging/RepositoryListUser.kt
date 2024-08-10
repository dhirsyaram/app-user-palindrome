package com.kuliah.apppalindromeuser.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.kuliah.apppalindromeuser.data.response.DataItem
import com.kuliah.apppalindromeuser.data.retrofit.ApiService
import kotlinx.coroutines.flow.Flow

class RepositoryListUser(private val apiService: ApiService) {

    fun getUsers(): Flow<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = { PagingListSource(apiService) }
        ).flow
    }
}