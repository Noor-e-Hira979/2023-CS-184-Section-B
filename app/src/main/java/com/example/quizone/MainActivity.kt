package com.example.quizone

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {  // ✅ Remove extra spaces

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Make sure you have a Button in activity_main.xml with id "btnBook"
        val btnBook = findViewById<Button>(R.id.btnBook)

        btnBook.setOnClickListener {
            // Navigate to BookAppointmentActivity
            startActivity(Intent(this@MainActivity, BookAppointmentActivity::class.java))
        }
    }
}