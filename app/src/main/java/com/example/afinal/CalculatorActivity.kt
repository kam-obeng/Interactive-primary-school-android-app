package com.example.afinal

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private var expression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calculator_layout)

        tvResult = findViewById(R.id.tvResult)

        // Set OnClickListener for all buttons
        val buttons = arrayOf(
            R.id.btnClear, R.id.btnDelete, R.id.btnEqual, R.id.btnAdd,
            R.id.btnSubtract, R.id.btnMultiply, R.id.btnDivide,
            R.id.btnDot, R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3,
            R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (buttonId in buttons) {
            findViewById<Button>(buttonId).setOnClickListener { handleButtonClick(it as Button) }
        }
    }

    private fun handleButtonClick(button: Button) {
        val buttonText = button.text.toString()

        when (buttonText) {
            "C" -> clear()
            "DEL" -> delete()
            "=" -> calculate()
            else -> appendToExpression(buttonText)
        }
    }

    private fun clear() {
        expression = ""
        tvResult.text = "0"
    }

    private fun delete() {
        if (expression.isNotEmpty()) {
            expression = expression.dropLast(1)
            tvResult.text = if (expression.isNotEmpty()) expression else "0"
        }
    }

    private fun appendToExpression(value: String) {
        expression += value
        tvResult.text = expression
    }

    private fun calculate() {
        // Using eval() function to evaluate arithmetic expression
        val result = try {
            expression.eval().toString()
        } catch (e: Exception) {
            "Error"
        }

        tvResult.text = result
        expression = result
    }

    // Extension function to evaluate arithmetic expressions
    private fun String.eval(): Double {
        return object : Any() {
            val tokens = this@eval.replace(" ", "").replace("[+\\-*/]".toRegex(), " $0 ").trim().split(" ")
            var pos = -1
            var token: String? = null

            fun nextToken(): String? {
                pos++
                return if (pos < tokens.size) {
                    tokens[pos]
                } else {
                    null
                }
            }

            fun eval(): Double {
                var v = eval2()
                while (token != null && (token == "+" || token == "-")) {
                    if (token == "+") {
                        token = nextToken()
                        v += eval2()
                    } else if (token == "-") {
                        token = nextToken()
                        v -= eval2()
                    }
                }
                return v
            }

            fun eval2(): Double {
                var v = eval3()
                while (token != null && (token == "*" || token == "/")) {
                    if (token == "*") {
                        token = nextToken()
                        v *= eval3()
                    } else if (token == "/") {
                        token = nextToken()
                        v /= eval3()
                    }
                }
                return v
            }

            fun eval3(): Double {
                token = nextToken()
                if (token == "(") {
                    val v = eval()
                    if (token != ")") {
                        throw RuntimeException("Expected )")
                    }
                    token = nextToken()
                    return v
                }
                return token!!.toDoubleOrNull() ?: throw RuntimeException("Expected number")
            }
        }.eval()
    }
}