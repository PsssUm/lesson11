package com.learneasy.lesson8

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.LinearLayout
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
            for (x in 0 until 101){
                runOnUiThread(Runnable {
                    val progress = (x.toFloat() / 100)
                    val params = progressLL.layoutParams
                    val progressWidth = (progress * width).toInt()
                    animateProgress(params.width, progressWidth, animateProgressLL)
                    params.width = progressWidth
                    progressLL.layoutParams = params
                    progressTV.text = "$x%"
                })
                Thread.sleep(100)
            }
        })
        thread.start()
    }
    private fun animateProgress(startWidth : Int, endWidth : Int, view : LinearLayout){
        val animator = ValueAnimator.ofInt(startWidth, endWidth).setDuration(100)
        animator.addUpdateListener {
            view.layoutParams.width = it.animatedValue as Int
            view.requestLayout()
        }
        val animatorSet = AnimatorSet()
        animatorSet.play(animator)
        animatorSet.start()
    }


}