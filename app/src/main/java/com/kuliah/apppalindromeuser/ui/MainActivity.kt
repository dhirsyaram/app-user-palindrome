package com.kuliah.apppalindromeuser.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kuliah.apppalindromeuser.R
import com.kuliah.apppalindromeuser.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mainBinding.btnCheck.setOnClickListener {
            val sentence = mainBinding.inputCheckPalindrome.text.toString()
            if (isPalindrome(sentence)) {
                showDialog("Palindrome")
            } else {
                showDialog("Not a Palindrome")
            }
        }

        mainBinding.btnNext.setOnClickListener {
            val username = mainBinding.inputUsername.text.toString()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(USER_KEY, username)
            startActivity(intent)
        }
    }

    private fun isPalindrome(sentence: String): Boolean {
        val cleanedSentence = sentence.replace(Regex("[^A-Za-z0-9]"), "").lowercase()
        return cleanedSentence == cleanedSentence.reversed()
    }

    private fun showDialog(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Palindrome Check")
            .setMessage(message)
            .setPositiveButton("OK", null)
            .show()
    }

    companion object {
        const val USER_KEY = "user_key"
    }
}