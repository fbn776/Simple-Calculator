package com.example.simplecalculator
import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.simplecalculator.R.id.*
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Different views
        val resultScreen = findViewById<TextView>(resultBar)
        val horizontalScrollView = findViewById<HorizontalScrollView>(resultBarScroller)
        val delKey = findViewById<TextView>(delKey)
        val acKey = findViewById<TextView>(acKey)
        val equalKey = findViewById<TextView>(equalKey)
        //Array containing the num keys from 0 to 9. Each at index corresponding to its number.
        val keys = arrayOf(
            findViewById<TextView>(num0),
            findViewById<TextView>(num1),
            findViewById<TextView>(num2),
            findViewById<TextView>(num3),
            findViewById<TextView>(num4),
            findViewById<TextView>(num5),
            findViewById<TextView>(num6),
            findViewById<TextView>(num7),
            findViewById<TextView>(num8),
            findViewById<TextView>(num9),

            findViewById<TextView>(plusKey),
            findViewById<TextView>(minusKey),
            findViewById<TextView>(multiplyKey),
            findViewById<TextView>(divideKey),

            findViewById<TextView>(decimalKey),
        )

        for(key in keys) {
            key.setOnClickListener {
                val currTxt = resultScreen.text.toString()
                if("Error" in currTxt) {
                    resultScreen.text = ""
                }

                resultScreen.text = (resultScreen.text.toString() + key.text.toString())
                //For auto scrolling to the end, when inputing
                horizontalScrollView.post {
                    horizontalScrollView.fullScroll(View.FOCUS_RIGHT)
                }
            }
        }

        //For deleting last entry;
        delKey.setOnClickListener {
            val currTxt = resultScreen.text.toString()
            //If ites `Error` then clear the display
            if("Error" in currTxt) {
                resultScreen.text = ""
            }else {
                if (currTxt != "") {
                    //Remove the last character and the assign it to the new result text;
                    resultScreen.text = currTxt.substring(0, currTxt.length - 1)
                }
            }
        }

        //For `all clear` (AC)
        acKey.setOnClickListener {
            //Set the result screen text to an empty string
            resultScreen.text = ""
        }

        equalKey.setOnClickListener {
            val screenContent: Queue<Char> = Queue<Char>()
            screenContent.pushAll(*(resultScreen.text.toString().toCharArray().toTypedArray()))
            val infixNot = InfixNotation().convert(screenContent)

            if(infixNot.hasDecimalError || infixNot.hasOperatorError || infixNot.hasInvalidCharError) {
                resultScreen.text = "Error"
            }else {
                val result = infixNot.result
                var ans = SolveInfix().solve(result)

                if(ans - ans.toInt() == 0F) {
                    resultScreen.text = ans.toInt().toString()
                }else {
                    resultScreen.text = ans.toString()
                }
            }
        }
    }
}