package com.example.smartshophugo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        etUsername = findViewById(R.id.etUsername)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener{
            val username = etUsername.text.toString()
            val password = etPassword.text.toString()
            login(username, password)
        }
    }

    fun login( username: String, password: String){
        if (username.isNotEmpty() && password.isNotEmpty()) {
            Toast.makeText(this, "Login exitoso", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Login fallido", Toast.LENGTH_SHORT).show()
        }
    }

}