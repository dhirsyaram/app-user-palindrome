package com.kuliah.apppalindromeuser.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuliah.apppalindromeuser.R
import com.kuliah.apppalindromeuser.data.adapter.LoadingFooterStateAdapter
import com.kuliah.apppalindromeuser.data.adapter.UserAdapter
import com.kuliah.apppalindromeuser.data.retrofit.ApiConfig
import com.kuliah.apppalindromeuser.databinding.ActivityThirdBinding
import com.kuliah.apppalindromeuser.paging.RepositoryListUser
import com.kuliah.apppalindromeuser.ui.viewmodel.ThirdViewModel
import com.kuliah.apppalindromeuser.ui.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ThirdActivity : AppCompatActivity() {

    private lateinit var thirdBinding: ActivityThirdBinding
    private lateinit var adapter: UserAdapter
    private val thirdViewModel: ThirdViewModel by viewModels {
        ViewModelFactory(repo)
    }
    private lateinit var repo: RepositoryListUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        thirdBinding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(thirdBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val apiService = ApiConfig.getApiService()
        repo = RepositoryListUser(apiService)

        thirdBinding.btnBackSecond.setOnClickListener {
            finish()
        }

        val layoutManager = LinearLayoutManager(this)
        thirdBinding.rvItemUser.setHasFixedSize(true)
        thirdBinding.rvItemUser.layoutManager = layoutManager
        val dividerItem = DividerItemDecoration(this, layoutManager.orientation)
        thirdBinding.rvItemUser.addItemDecoration(dividerItem)

        adapter = UserAdapter { username ->
            val resultIntent = Intent().apply {
                putExtra(UserAdapter.USERNAME, username)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        thirdViewModel.isLoading.observe(this) { isLoading ->
            showLoading(isLoading)
        }

        thirdBinding.rvItemUser.adapter = adapter.withLoadStateFooter(
            footer = LoadingFooterStateAdapter { adapter.retry() }
        )

        lifecycleScope.launch {
            thirdViewModel.userList.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }

        adapter.addLoadStateListener { loadState ->
            val isLoading = loadState.source.refresh is LoadState.Loading
            showLoading(isLoading)

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.refresh as? LoadState.Error

            errorState?.let {
                Toast.makeText(this, "Error: ${it.error}", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun showLoading(isLoading: Boolean) {
        thirdBinding.pbThird.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}