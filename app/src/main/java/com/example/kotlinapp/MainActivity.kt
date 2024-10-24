package com.example.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var inputNumber: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val allNumbersButton: Button = findViewById(R.id.allNumbersButton)
        val specificNumberButton: Button = findViewById(R.id.specificNumberButton)
        inputNumber = findViewById(R.id.inputNumber)

        allNumbersButton.setOnClickListener {
            val intent = Intent(this, ExerciseActivity::class.java)
            intent.putExtra("type", "all")
            startActivity(intent)
        }

        specificNumberButton.setOnClickListener {
            val number = inputNumber.text.toString().toIntOrNull()
            if (number != null && number in 2..9) {
                val intent = Intent(this, ExerciseActivity::class.java)
                intent.putExtra("type", "specific")
                intent.putExtra("specificNumber", number)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Введите число от 2 до 9", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Сохранение данных перед поворотом экрана
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("inputNumber", inputNumber.text.toString())
    }
    // Восстановления состояния
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedNumber = savedInstanceState.getString("inputNumber")
        inputNumber.setText(savedNumber)
    }
}
