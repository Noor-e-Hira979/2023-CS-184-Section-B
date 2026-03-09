package com.example.quizone

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class BookAppointmentActivity : AppCompatActivity() {

    lateinit var etName: EditText
    lateinit var etPhone: EditText
    lateinit var etEmail: EditText
    lateinit var spinnerType: Spinner
    lateinit var btnDate: Button
    lateinit var btnTime: Button
    lateinit var radioGender: RadioGroup
    lateinit var checkTerms: CheckBox
    lateinit var btnConfirm: Button

    var selectedDate = ""
    var selectedTime = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_appointment)

        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        spinnerType = findViewById(R.id.spinnerType)
        btnDate = findViewById(R.id.btnDate)
        btnTime = findViewById(R.id.btnTime)
        radioGender = findViewById(R.id.radioGender)
        checkTerms = findViewById(R.id.checkTerms)
        btnConfirm = findViewById(R.id.btnConfirm)

        setupSpinner()
        selectDate()
        selectTime()
        confirmBooking()
    }

    private fun setupSpinner() {

        val types = arrayOf(
            "Select Appointment Type",
            "Doctor Consultation",
            "Dentist Appointment",
            "Eye Specialist",
            "Skin Specialist",
            "General Checkup"
        )

        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_dropdown_item,
            types
        )

        spinnerType.adapter = adapter
    }

    private fun selectDate() {

        btnDate.setOnClickListener {

            val calendar = Calendar.getInstance()

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(
                this,
                { _, y, m, d ->

                    selectedDate = "$d/${m+1}/$y"
                    btnDate.text = selectedDate

                },
                year, month, day
            )

            datePicker.show()
        }
    }

    private fun selectTime() {

        btnTime.setOnClickListener {

            val calendar = Calendar.getInstance()

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(
                this,
                { _, h, m ->

                    selectedTime = "$h:$m"
                    btnTime.text = selectedTime

                },
                hour, minute, true
            )

            timePicker.show()
        }
    }

    private fun confirmBooking() {

        btnConfirm.setOnClickListener {

            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val email = etEmail.text.toString()
            val type = spinnerType.selectedItem.toString()

            val genderId = radioGender.checkedRadioButtonId

            if (name.isEmpty()) {
                etName.error = "Enter Name"
                return@setOnClickListener
            }

            if (phone.isEmpty()) {
                etPhone.error = "Enter Phone"
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                etEmail.error = "Enter Email"
                return@setOnClickListener
            }

            if (type == "Select Appointment Type") {
                Toast.makeText(this,"Select Appointment Type",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (genderId == -1) {
                Toast.makeText(this,"Select Gender",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!checkTerms.isChecked) {
                Toast.makeText(this,"Accept Terms",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val genderRadio = findViewById<RadioButton>(genderId)
            val gender = genderRadio.text.toString()

            val intent = Intent(this, ConfirmationActivity::class.java)

            intent.putExtra("name",name)
            intent.putExtra("phone",phone)
            intent.putExtra("email",email)
            intent.putExtra("type",type)
            intent.putExtra("date",selectedDate)
            intent.putExtra("time",selectedTime)
            intent.putExtra("gender",gender)

            startActivity(intent)
        }
    }
}