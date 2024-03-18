package com.example.afinal
import android.view.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnNotes = findViewById<Button>(R.id.btnNotes)
        val btnQuiz = findViewById<Button>(R.id.btnQuiz)
        val btnCalculator = findViewById<Button>(R.id.btnCalculator) // Add this line
        val btnBBC = findViewById<Button>(R.id.btnBBC)

        btnNotes.setOnClickListener {
            startActivity(Intent(this, NotesActivity::class.java))
        }

        btnQuiz.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        // Add OnClickListener for Calculator Button
        btnCalculator.setOnClickListener {
            startActivity(Intent(this, CalculatorActivity::class.java))
        }
        btnBBC.setOnClickListener {
            openWebView(it)
        }
    }

    // Function to open WebView
    fun openWebView(view: View) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra("URL", "https://www.bbc.co.uk/bitesize/primary")
        startActivity(intent)
    }
}