package com.example.hungerhalter.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.hungerhalter.R

class RegisterActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etEmailAddress: EditText
    lateinit var etMobileNumber: EditText
    lateinit var etDeliveryAddress: EditText
    lateinit var etPassword: EditText
    lateinit var etConfirmPassword: EditText
    lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
    }
}