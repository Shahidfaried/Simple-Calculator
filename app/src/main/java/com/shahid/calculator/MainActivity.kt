package com.shahid.calculator

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    val arrOperators = arrayListOf("+", "-", "/", "x")
    var equalToFlag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txt0 = findViewById<TextView>(R.id.txt0)
        val txtEqual = findViewById<TextView>(R.id.txtEqual)
        val txtClear = findViewById<TextView>(R.id.txtClear)
        val txtDivide = findViewById<TextView>(R.id.txtDivide)
        val txtMultiply = findViewById<TextView>(R.id.txtMultiply)
        val txtSubtract = findViewById<TextView>(R.id.txtSubstract)
        val txtAddition = findViewById<TextView>(R.id.txtAddition)
        val txt7 = findViewById<TextView>(R.id.txt7)
        val txt8 = findViewById<TextView>(R.id.txt8)
        val txt9 = findViewById<TextView>(R.id.txt9)
        val txt4 = findViewById<TextView>(R.id.txt4)
        val txt5 = findViewById<TextView>(R.id.txt5)
        val txt6 = findViewById<TextView>(R.id.txt6)
        val txt1 = findViewById<TextView>(R.id.txt1)
        val txt2 = findViewById<TextView>(R.id.txt2)
        val txt3 = findViewById<TextView>(R.id.txt3)
        val txtdot = findViewById<TextView>(R.id.txtdot)
        val txtDel = findViewById<TextView>(R.id.txtDel)
        val txtResult = findViewById<TextView>(R.id.txtResult)
        val txtExpression = findViewById<TextView>(R.id.txtExpression)




        txt0.setOnClickListener {
            buildExpression("0", false)
        }
        txt1.setOnClickListener {
            buildExpression("1", false)
        }
        txt2.setOnClickListener {
            buildExpression("2", false)
        }
        txt3.setOnClickListener {
            buildExpression("3", false)
        }
        txt4.setOnClickListener {
            buildExpression("4", false)
        }
        txt5.setOnClickListener {
            buildExpression("5", false)
        }
        txt6.setOnClickListener {
            buildExpression("6", false)
        }
        txt7.setOnClickListener {
            buildExpression("7", false)
        }
        txt8.setOnClickListener {
            buildExpression("8",false)
        }
        txt9.setOnClickListener {
            buildExpression("9",false)
        }
        txtdot.setOnClickListener {
            buildExpression(".", false)
        }
        txtAddition.setOnClickListener {
            buildExpression("+", true)
        }

        txtSubtract.setOnClickListener {
            buildExpression("-",true)
        }
        txtMultiply.setOnClickListener {
            buildExpression("*",true)
        }
        txtDivide.setOnClickListener {
            buildExpression("/", true)
        }
        txtClear.setOnClickListener {
            txtExpression.text = ""
            txtResult.text = ""
        }

        txtEqual.setOnClickListener{
            val text = txtExpression.text.toString()
            val expression = ExpressionBuilder(text).build()
            val result = expression.evaluate().toLong()
            txtResult.text = result.toString()

            equalToFlag = true

        }

        txtDel.setOnClickListener {
            val text = txtExpression.text.toString()
            if(text.isNotEmpty()){
                txtExpression.text = text.dropLast(1)
            }
            txtResult.text = ""
        }

    }
    private fun buildExpression(str: String, canClear: Boolean) {
        val txtExpression = findViewById<TextView>(R.id.txtExpression)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        if (equalToFlag){
            txtExpression.text = txtResult.text.toString()
        }

        val text = txtExpression.text.toString()

        if(canClear && arrOperators.contains(text[text.length-1].toString())){
            Toast.makeText(this, "Consecutive Operators Not Allowed", Toast.LENGTH_LONG).show()
        }else {
            txtResult.text =""
            txtExpression.append(str)
            equalToFlag = false
        }

    }
}