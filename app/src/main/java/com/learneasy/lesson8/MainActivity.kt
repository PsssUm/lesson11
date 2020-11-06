package com.learneasy.lesson8

import android.os.Bundle
import android.util.DisplayMetrics
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val width = displayMetrics.widthPixels

        val thread = Thread(Runnable {
            for (x in 0 until 100){
                runOnUiThread(Runnable {
                    val progress = (x.toFloat() / 100)
                    val params = progressLL.layoutParams
                    params.width = (progress * width).toInt()
                    progressLL.layoutParams = params
                    progressTV.text = "$x%"
                })
                Thread.sleep(100)
            }
        })
        thread.start()
    }
}