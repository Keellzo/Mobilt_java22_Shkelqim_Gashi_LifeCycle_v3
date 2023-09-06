package com.example.mobilt_java22_shkelqim_gashi_lifecycle_v3_master

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Context
import android.content.Intent

import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.RadioGroup

import com.google.android.material.bottomnavigation.BottomNavigationView

class AccInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acc_info)

        val ageInput = findViewById<EditText>(R.id.ageInput)
        val licenseCheckBox = findViewById<CheckBox>(R.id.licenseCheckBox)
        val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)
        val emailInput = findViewById<EditText>(R.id.emailInput)
        val occupationSpinner = findViewById<Spinner>(R.id.occupationSpinner)
        val submitButton = findViewById<Button>(R.id.submitButton)
        val ageDisplay = findViewById<TextView>(R.id.ageDisplay)
        val licenseDisplay = findViewById<TextView>(R.id.licenseDisplay)
        val genderDisplay = findViewById<TextView>(R.id.genderDisplay)
        val emailDisplay = findViewById<TextView>(R.id.emailDisplay)
        val occupationDisplay = findViewById<TextView>(R.id.occupationDisplay)

        val sharedPreferences = getSharedPreferences("MyApp", Context.MODE_PRIVATE)

        val occupations = arrayOf("Student", "Software Engineer", "Designer", "Other")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, occupations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        occupationSpinner.adapter = adapter

        val savedAge = sharedPreferences.getInt("age", 0)
        val savedHasLicense = sharedPreferences.getBoolean("hasLicense", false)
        val savedGenderId = sharedPreferences.getInt("genderId", 0)
        val savedEmail = sharedPreferences.getString("email", "")
        val savedOccupation = sharedPreferences.getString("occupation", "Student") // New

        ageInput.setText(savedAge.toString())
        licenseCheckBox.isChecked = savedHasLicense
        genderRadioGroup.check(savedGenderId)
        emailInput.setText(savedEmail)
        occupationSpinner.setSelection(occupations.indexOf(savedOccupation)) // New

        submitButton.setOnClickListener {
            val age = ageInput.text.toString().toIntOrNull()
            val hasLicense = licenseCheckBox.isChecked
            val selectedGenderId = genderRadioGroup.checkedRadioButtonId
            val email = emailInput.text.toString()
            val selectedOccupation = occupationSpinner.selectedItem.toString() // New

            val editor = sharedPreferences.edit()
            editor.putInt("age", age ?: 0)
            editor.putBoolean("hasLicense", hasLicense)
            editor.putInt("genderId", selectedGenderId)
            editor.putString("email", email)
            editor.putString("occupation", selectedOccupation)
            editor.apply()


            ageDisplay.text = "Age: ${age ?: "N/A"}"
            licenseDisplay.text = "Has License: $hasLicense"
            emailDisplay.text = "Email: $email"
            occupationDisplay.text = "Occupation: $selectedOccupation"

            val genderText = when (selectedGenderId) {
                R.id.maleRadioButton -> "Male"
                R.id.femaleRadioButton -> "Female"
                else -> "N/A"
            }
            genderDisplay.text = "Gender: $genderText"
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_register -> {
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_acc_info -> {
                    true
                }
                else -> false
            }
        }
    }
}