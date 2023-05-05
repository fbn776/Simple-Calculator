package com.example.simplecalculator
//
//import android.os.Bundle
//import com.google.android.material.snackbar.Snackbar
//import androidx.appcompat.app.AppCompatActivity
//import androidx.core.view.WindowCompat
//import androidx.navigation.findNavController
//import androidx.navigation.ui.AppBarConfiguration
//import androidx.navigation.ui.navigateUp
//import androidx.navigation.ui.setupActionBarWithNavController
//import android.view.Menu
//import android.view.MenuItem
//import com.example.simplecalculator.databinding.ActivityMainBinding
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        }
//    }
//}


import android.os.Bundle
import android.view.View
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.simplecalculator.R.id.*

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
            if(currTxt != "") {
                //Remove the last character and the assign it to the new result text;
                resultScreen.text = currTxt.substring(0, currTxt.length - 1)
            }
        }

        //For `all clear` (AC)
        acKey.setOnClickListener {
            //Set the result screen text to an empty string
            resultScreen.text = ""
        }
    }
}