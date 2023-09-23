package com.example.brainbyte.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brainbyte.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private var binding: ActivityLoginBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.tvSignUp?.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding?.btLogin?.setOnClickListener {
            val email = binding?.tvEmailAddress?.text.toString()
            val password = binding?.etPasswordLogin?.text.toString()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}