package com.example.mobilt_java22_shkelqim_gashi_lifecycle_v3_master

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)

        sharedPreferences = getSharedPreferences("MyApp", Context.MODE_PRIVATE)

        val savedUsername = sharedPreferences.getString("main_username", "")
        val savedPassword = sharedPreferences.getString("main_password", "")

        usernameInput.setText(savedUsername)
        passwordInput.setText(savedPassword)

        val correctUsername = "Kelly"
        val correctPassword = "password123"

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true
                R.id.nav_register -> {
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.nav_acc_info -> {
                    val intent = Intent(this, AccInfoActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }

        loginButton.setOnClickListener {
            val enteredUsername = usernameInput.text.toString()
            val enteredPassword = passwordInput.text.toString()

            if (enteredUsername == correctUsername && enteredPassword == correctPassword) {
                val intent = Intent(this, AccInfoActivity::class.java)
                startActivity(intent)
                Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Login failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onPause() {
        super.onPause()

        val username = usernameInput.text.toString()
        val password = passwordInput.text.toString()

        val editor = sharedPreferences.edit()

        editor.putString("main_username", username)
        editor.putString("main_password", password)
        editor.apply()
    }
}
