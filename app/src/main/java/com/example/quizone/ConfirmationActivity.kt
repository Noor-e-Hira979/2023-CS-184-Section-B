package com.example.quizone

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirmation)

        val textSummary = findViewById<TextView>(R.id.textSummary)

        val name = intent.getStringExtra("name")
        val phone = intent.getStringExtra("phone")
        val email = intent.getStringExtra("email")
        val type = intent.getStringExtra("type")
        val date = intent.getStringExtra("date")
        val time = intent.getStringExtra("time")
        val gender = intent.getStringExtra("gender")

        textSummary.text = """
Full Name: $name

Phone: $phone

Email: $email

Appointment Type: $type

Date: $date

Time: $time

Gender: $gender
        """.trimIndent()
    }
}