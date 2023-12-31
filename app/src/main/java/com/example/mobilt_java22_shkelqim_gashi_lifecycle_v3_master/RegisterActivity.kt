package com.example.mobilt_java22_shkelqim_gashi_lifecycle_v3_master

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class RegisterActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var usernameInput: EditText
    private lateinit var passwordInput: EditText
    private lateinit var emailInput: EditText
    private lateinit var ssnInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        usernameInput = findViewById(R.id.usernameInput)
        passwordInput = findViewById(R.id.passwordInput)
        emailInput = findViewById(R.id.emailInput)
        ssnInput = findViewById(R.id.ssnInput)
        val registerButton = findViewById<Button>(R.id.registerButton)

        sharedPreferences = getSharedPreferences("RegisterPrefs", Context.MODE_PRIVATE)

        val savedUsername = sharedPreferences.getString("username", "")
        val savedPassword = sharedPreferences.getString("password", "")
        val savedEmail = sharedPreferences.getString("registerEmail", "")
        val savedSSN = sharedPreferences.getString("ssn", "")

        usernameInput.setText(savedUsername)
        passwordInput.setText(savedPassword)
        emailInput.setText(savedEmail)
        ssnInput.setText(savedSSN)

        val usernameDisplay = findViewById<TextView>(R.id.usernameDisplay)
        val emailDisplay = findViewById<TextView>(R.id.emailDisplayRegister)



        registerButton.setOnClickListener {
            val email = emailInput.text.toString()
            val username = usernameInput.text.toString()
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

    override fun onPause() {
        super.onPause()

        val username = usernameInput.text.toString()
        val password = passwordInput.text.toString()
        val email = emailInput.text.toString()
        val ssn = ssnInput.text.toString()

        val editor = sharedPreferences.edit()

        editor.putString("username", username)
        editor.putString("password", password)
        editor.putString("registerEmail", email)
        editor.putString("ssn", ssn)
        editor.apply()
    }
}