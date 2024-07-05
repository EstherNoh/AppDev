package com.jumi.practice_again

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.os.PowerManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import java.util.Random
import java.util.Timer
import kotlin.concurrent.timer
import android.view.KeyEvent
import android.view.View
import kotlinx.coroutines.delay
import org.w3c.dom.Text


class MainActivity : AppCompatActivity() {
    fun who(num : Int, array: DoubleArray): IntArray {

        var arr = IntArray(num)

        for (i : Int in 1..num){
            arr[i-1] = i
        }

        var temp : Double =0.0
        var temp_num : Int = 0

        for (i : Int in 0 until num){
            for(j :Int in 0 until num){
                if(array[i]<array[j]) {
                    temp = array[i]
                    array[i] = array[j]
                    array[j] = temp

                    temp_num = arr[i]
                    arr[i] = arr[j]
                    arr[j] = temp_num

                }
            }
        }




        return arr
    }

    fun end(rank: IntArray) {
        val textViewIds = listOf(R.id.first, R.id.second, R.id.third, R.id.four, R.id.five, R.id.six)
        val textViews = textViewIds.map { findViewById<TextView>(it) }

        val bu : Button = findViewById(R.id.button2)



        // rank 배열의 크기만큼 순회하면서 TextView에 순위를 표시
        for (i in rank.indices) {
            if (i < textViews.size) { // rank의 크기가 6보다 작을 경우에만 해당하는 TextView에 순위를 표시
                textViews[i].text = "도전자 ${rank[i]}"
            }
        }

        // rank 배열의 크기가 6보다 작을 경우 나머지 TextView를 숨기거나 초기화할 수 있음
        if (rank.size < 6) {
            for (i in rank.size until textViews.size) {
                textViews[i].visibility = View.GONE // 숨기기 예시
            }

        }
        bu.setOnClickListener{
            setContentView(R.layout.second)
            first()
        }




    }

        // rank 배열의 크기가 6보다 작으면 나머지 TextView를 초기화합니다.



    fun second(num: Int) {
        var ti: Timer? = null
        val qu: TextView = findViewById(R.id.quest)
        val an : TextView = findViewById(R.id.answer)
        val re: TextView = findViewById(R.id.record)
        val but: Button = findViewById(R.id.button)
        val count: TextView = findViewById(R.id.count)
        var run: Boolean = false
        var sec: Int = 0
        val random = Random()
        var r = 0

        var hownum: Int = 1
        var doubleArray  = DoubleArray(num)


        but.setOnClickListener {
            run = !run
            re.text = "0.00"
            if (run == true) {
                count.text = "참가자 "+(hownum.toString())
                but.text = "Stop"
                an.text = "0.00"
                sec = 0
                r = random.nextInt(900) + 100
                qu.text = (r.toDouble() / 100).toString()
                ti = kotlin.concurrent.timer(period = 10) {
                    sec++

                }
            } else {

                ti?.cancel()
                an.text = (sec.toDouble()/100).toString()
                var end: Int = (r - sec)
                if (end < 0) {
                    re.text = "Fail"
                    doubleArray[hownum-1] = -10.0
                } else {
                    re.text = (end.toDouble() / 100).toString()
                    doubleArray[hownum-1] = (end.toDouble()/100)
                }
                but.text = "Restart"

                if (hownum==num) {


                    setContentView(R.layout.end_interface)
                    end(who(num,doubleArray))

                }
                hownum++
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