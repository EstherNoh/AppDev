package com.jumi.practice_again

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.delay
import java.util.Random
import java.util.Timer
import kotlin.concurrent.timer
import android.view.KeyEvent


class MainActivity : AppCompatActivity() {
    fun end(rank: IntArray ){



    }
    fun second(num: Int) {
        var ti: Timer? = null
        val qu: TextView = findViewById(R.id.quest)
        val my: TextView = findViewById(R.id.mytime)
        val re: TextView = findViewById(R.id.record)
        val but: Button = findViewById(R.id.button)
        val count: TextView = findViewById(R.id.count)
        var run: Boolean = false
        var sec: Int = 0
        val random = Random()
        var r = 0

        var hownum: Int = 1
        var doubleArray = Array(5) { 0 }


        but.setOnClickListener {
            run = !run
            re.text = "0.00"
            if (run == true) {
                count.text = hownum.toString()
                but.text = "Stop"
                sec = 0
                r = random.nextInt(1000) + 1
                qu.text = (r.toDouble() / 100).toString()
                ti = kotlin.concurrent.timer(period = 10) {
                    sec++
                    my.text = (sec.toDouble() / 100).toString()
                }
            } else {
                hownum++
                ti?.cancel()
                var end: Int = (r - sec)
                if (end < 0) {
                    re.text = "Fail"
                } else {
                    re.text = (end.toDouble() / 100).toString()
                }
                but.text = "Restart"
            }


            if (hownum>num) {
                count.text = hownum.toString()
                return@setOnClickListener
                setContentView(R.layout.end_interface)

            }

        }




    }

    fun first() {
        val editText = findViewById<EditText>(R.id.input)
        val tv_1 = findViewById(R.id.input) as TextView
        var inputText : String=""
        var istrue : Boolean = true

        editText.setOnKeyListener { v, keyCode, event ->

            // Enter 키를 눌렀을 때만 처리
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN) {
                inputText = editText.text.toString()
                // 입력값이 1~6인지 확인
                if (inputText.toIntOrNull() in 1..6) {
                    tv_1.text = "Selected number: $inputText"
                    setContentView(R.layout.activity_main)
                    second(inputText.toInt())

                } else {
                    tv_1.text = "Please enter a number between 1 and 6"
                }
                true  // 이벤트를 처리했음을 반환
            } else {
                false  // 다른 키 입력은 처리하지 않음
            }
        }



    }


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.second)

        first()

    }
}