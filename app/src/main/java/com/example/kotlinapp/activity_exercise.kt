package com.example.kotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class ExerciseActivity : AppCompatActivity() {

    private var correctAnswers = 0
    private var currentQuestion = 0
    private var totalQuestions = 20
    private var number1 = 0
    private var number2 = 0
    private var specificNumber: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        val questionText: TextView = findViewById(R.id.questionText)
        val answerInput: EditText = findViewById(R.id.answerInput)
        val submitButton: Button = findViewById(R.id.submitButton)
        val resultText: TextView = findViewById(R.id.resultText)

        val type = intent.getStringExtra("type")
        specificNumber = intent.getIntExtra("specificNumber", -1)

        submitButton.setOnClickListener {
            val answer = answerInput.text.toString().toIntOrNull()
            if (answer != null) {
                if (answer == number1 * number2) {
                    correctAnswers++
                    resultText.text = "Правильный ответ"
                } else {
                    resultText.text = "Неверный ответ"
                }

                currentQuestion++
                if (currentQuestion < totalQuestions) {
                    generateNewQuestion(questionText)
                } else {
                    val percentage = (correctAnswers * 100) / totalQuestions
                    resultText.text = "Тест завершен! Правильных ответов: $percentage%"
                    submitButton.isEnabled = false
                }

                answerInput.text.clear()
            } else {
                resultText.text = "Введите число"
            }
        }

        generateNewQuestion(questionText)
    }

    private fun generateNewQuestion(questionText: TextView) {
        number1 = if (specificNumber != -1) specificNumber!! else Random.nextInt(2, 10)
        number2 = Random.nextInt(2, 10)
        questionText.text = "$number1 * $number2 = ?"
    }
}
