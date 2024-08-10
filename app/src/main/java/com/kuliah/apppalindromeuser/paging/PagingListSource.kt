package com.kuliah.apppalindromeuser.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kuliah.apppalindromeuser.data.response.DataItem
import com.kuliah.apppalindromeuser.data.retrofit.ApiService
import retrofit2.HttpException
import java.io.IOException

class PagingListSource(private val apiService: ApiService): PagingSource<Int, DataItem>() {
    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        val page = params.key ?: 1
        return try {
            val response = apiService.getListUser(page = page)
            val users = response.body()?.data ?: emptyList()
            LoadResult.Page(
                data = users,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (users.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}