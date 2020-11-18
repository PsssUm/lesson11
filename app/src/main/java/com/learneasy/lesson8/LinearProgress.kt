package com.learneasy.lesson8

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.progress.view.*

class LinearProgress @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var animateProgressLL : LinearLayout
    private var startWidth = 0
    init {
        val view = LayoutInflater.from(context).inflate(R.layout.progress, this, true)
        animateProgressLL = view.animateProgressLL
    }
    public fun animateProgress(endWidth : Int){
        val animator = ValueAnimator.ofInt(startWidth, endWidth).setDuration(100)
        animator.addUpdateListener {
            animateProgressLL.layoutParams.width = it.animatedValue as Int
            animateProgressLL.requestLayout()
        }
        val animatorSet = AnimatorSet()
        animatorSet.play(animator)
        animatorSet.start()
        startWidth = endWidth
    }
}