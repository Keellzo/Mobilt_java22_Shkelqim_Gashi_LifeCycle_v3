package com.example.mobilt_java22_shkelqim_gashi_lifecycle_v3_master

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val usernameInput = findViewById<EditText>(R.id.usernameInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val ssnInput = findViewById<EditText>(R.id.ssnInput)
        val registerButton = findViewById<Button>(R.id.registerButton)

        val sharedPreferences = getSharedPreferences("MyApp", Context.MODE_PRIVATE)

        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")
        val savedEmail = sharedPreferences.getString("email", "")
        val savedSSN = sharedPreferences.getString("ssn", "")

        usernameInput.setText(savedUsername)
        passwordInput.setText(savedPassword)
        emailInput.setText(savedEmail)
        ssnInput.setText(savedSSN)

        val usernameDisplay = findViewById<TextView>(R.id.usernameDisplay)
        val emailDisplay = findViewById<TextView>(R.id.emailDisplayRegister)



        registerButton.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()
            val email = emailInput.text.toString()
            val ssn = ssnInput.text.toString()


            val editor = sharedPreferences.edit()

            editor.putString("username", username)
            editor.putString("password", password)
            editor.putString("email", email)
            editor.putString("ssn", ssn)
            editor.apply()

            usernameDisplay.text = "Username: $username"
            emailDisplay.text = "Email: $email"
        }



        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_register -> true
                R.id.nav_acc_info -> {
                    val intent = Intent(this, AccInfoActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}