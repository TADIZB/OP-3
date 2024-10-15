package com.example.caculator

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var result: android.widget.TextView
    private var currentInput = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        result = findViewById(R.id.editTextNumber)
        val buttons = listOf(
            R.id.button34, R.id.button29, R.id.button30, R.id.button31, R.id.button25,
            R.id.button26, R.id.button27, R.id.button21, R.id.button22, R.id.button23
        )

        for (id in buttons) {
            findViewById<Button>(id).setOnClickListener { appendNumber((it as Button).text.toString()) }
        }

        findViewById<Button>(R.id.button32).setOnClickListener { chooseOperator("+") }
        findViewById<Button>(R.id.button28).setOnClickListener { chooseOperator("-") }
        findViewById<Button>(R.id.button24).setOnClickListener { chooseOperator("*") }
        findViewById<Button>(R.id.button5).setOnClickListener { chooseOperator("/") }

        findViewById<Button>(R.id.button36).setOnClickListener { calculateResult() }
        findViewById<Button>(R.id.buttonCE).setOnClickListener { clearInput() }
    }

    private fun appendNumber(number: String) {
        currentInput += number
        result.text = currentInput
    }

    private fun chooseOperator(selectedOperator: String) {
        currentInput += selectedOperator
        result.text = currentInput
    }

    private fun calculateResult() {
        val function = result.text
        val res = ExpressionBuilder(function.toString()).build().evaluate()
        result.text = res.toString()
        currentInput = ""

    }

    private fun clearInput() {
        currentInput = ""
        result.text = "0"
    }

}