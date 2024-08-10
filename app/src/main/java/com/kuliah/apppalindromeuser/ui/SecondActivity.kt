package com.kuliah.apppalindromeuser.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kuliah.apppalindromeuser.R
import com.kuliah.apppalindromeuser.data.adapter.UserAdapter
import com.kuliah.apppalindromeuser.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var secondBinding: ActivitySecondBinding

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val username = result.data?.getStringExtra(UserAdapter.USERNAME)
            secondBinding.tvSelectedUsername.text = username
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        secondBinding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(secondBinding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val usernameLogin = intent.getStringExtra(MainActivity.USER_KEY)
        if (usernameLogin.isNullOrEmpty()) {
            "Guest".also { secondBinding.tvUsernameSecond.text = it }
        } else {
            secondBinding.tvUsernameSecond.text = usernameLogin
        }

        secondBinding.btnBack.setOnClickListener { finish() }
        secondBinding.btnChooseUser.setOnClickListener {
            val intent = Intent(this, ThirdActivity::class.java)
            resultLauncher.launch(intent)
        }
    }
}



