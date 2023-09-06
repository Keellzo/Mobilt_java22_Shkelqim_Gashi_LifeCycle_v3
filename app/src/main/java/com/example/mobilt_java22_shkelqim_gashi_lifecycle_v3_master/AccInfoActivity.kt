package com.example.mobilt_java22_shkelqim_gashi_lifecycle_v3_master

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences

import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.RadioGroup

import com.google.android.material.bottomnavigation.BottomNavigationView

class AccInfoActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var ageInput: EditText
    private lateinit var licenseCheckBox: CheckBox
    private lateinit var genderRadioGroup: RadioGroup
    private lateinit var emailInput: EditText
    private lateinit var occupationSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acc_info)

        ageInput = findViewById(R.id.ageInput)
        licenseCheckBox = findViewById(R.id.licenseCheckBox)
        genderRadioGroup = findViewById(R.id.genderRadioGroup)
        emailInput = findViewById(R.id.emailInput)
        occupationSpinner = findViewById(R.id.occupationSpinner)

        sharedPreferences = getSharedPreferences("MyApp", Context.MODE_PRIVATE)

        val occupations = arrayOf("Student", "Software Engineer", "Designer", "Other")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, occupations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        occupationSpinner.adapter = adapter

        val savedAge = sharedPreferences.getInt("age", 0)
        val savedHasLicense = sharedPreferences.getBoolean("hasLicense", false)
        val savedGenderId = sharedPreferences.getInt("genderId", 0)
        val savedEmail = sharedPreferences.getString("email", "")
        val savedOccupation = sharedPreferences.getString("occupation", "Student")

        ageInput.setText(savedAge.toString())
        licenseCheckBox.isChecked = savedHasLicense
        genderRadioGroup.check(savedGenderId)
        emailInput.setText(savedEmail)
        occupationSpinner.setSelection(occupations.indexOf(savedOccupation))

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
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

    override fun onPause() {
        super.onPause()

        val age = ageInput.text.toString().toIntOrNull()
        val hasLicense = licenseCheckBox.isChecked
        val selectedGenderId = genderRadioGroup.checkedRadioButtonId
        val email = emailInput.text.toString()
        val selectedOccupation = occupationSpinner.selectedItem.toString()

        val editor = sharedPreferences.edit()

        editor.putInt("age", age ?: 0)
        editor.putBoolean("hasLicense", hasLicense)
        editor.putInt("genderId", selectedGenderId)
        editor.putString("email", email)
        editor.putString("occupation", selectedOccupation)
        editor.apply()
    }
}
