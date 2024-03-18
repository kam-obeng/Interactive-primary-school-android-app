package com.example.afinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {
    private val questions = arrayOf(
        "What animal says 'Meow'?",
        "What animal says 'Woof'?",
        "What animal is known for its trunk?",
        "What animal is the king of the jungle?",
        "What animal is known for its black and white stripes?"
    )
    private val answers = arrayOf("Cat", "Dog", "Elephant", "Lion", "Zebra")
    private var currentQuestionIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val textViewQuestion = findViewById<TextView>(R.id.textViewQuestion)
        val editTextAnswer = findViewById<EditText>(R.id.editTextAnswer)
        val buttonSubmit = findViewById<Button>(R.id.buttonSubmit)

        textViewQuestion.text = questions[currentQuestionIndex]

        buttonSubmit.setOnClickListener {
            val userAnswer = editTextAnswer.text.toString()
            checkAnswer(userAnswer)
            if (currentQuestionIndex < questions.size - 1) {
                currentQuestionIndex++
                textViewQuestion.text = questions[currentQuestionIndex]
                editTextAnswer.text.clear()
            } else {
                // End of quiz
                // Implement code to show quiz result or navigate to another activity
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }

    private fun checkAnswer(userAnswer: String) {
        val correctAnswer = answers[currentQuestionIndex]
        if (userAnswer.equals(correctAnswer, ignoreCase = true)) {
            // Correct answer
            // Implement your logic here (e.g., show a toast)
        } else {
            // Incorrect answer
            // Implement your logic here (e.g., show a toast)
        }
    }
}
