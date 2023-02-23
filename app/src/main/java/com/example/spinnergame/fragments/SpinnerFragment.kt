package com.example.spinnergame.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.spinnergame.R
import com.example.spinnergame.helpers.getCurrentTime
import com.example.spinnergame.model.Result

import com.example.spinnergame.viewmodel.ResultViewModel
import java.util.*


class SpinnerFragment : Fragment(),Animation.AnimationListener {
val viewModels:ResultViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_spinner, container, false)
    }


    private var count = 0
    private var flag = false

    private var powerButton: ImageView? = null

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        /**get Id*/
        powerButton =view. findViewById(R.id.powerButton)
        powerButton!!.setOnTouchListener(PowerTouchListener())
        intSpinner()
viewModels.sumData.observe(viewLifecycleOwner){
view.findViewById<TextView>(R.id.commonCountText).text="Total score : ${it}"

}


    }

    /**
     * All the vars you need
     * */
    val prizes = intArrayOf(200,1000,600,1000,500,400,200,700,3000,400,1000,1200)
    private var mSpinDuration :Long = 0
    private var mSpinRevolution = 0f
    var pointerImageView: ImageView? = null
    var infoText: TextView? = null
    var prizeText = "N/A"
    var data:Int =0

    private fun intSpinner() {
        pointerImageView =view?. findViewById(R.id.imageWheel)
        infoText =view?. findViewById(R.id.infoText)
    }

    fun startSpinner() {
        mSpinRevolution = 3600f
        mSpinDuration = 5000

        if (count >= 30){
            mSpinDuration = 1000
            mSpinRevolution = (3600 * 2).toFloat()
        }
        if (count >= 60){
            mSpinDuration = 15000
            mSpinRevolution = (3600 * 3).toFloat()

        }

        // Final point of rotation defined right here

        val end = Math.floor(Math.random() * 3600).toInt() // random : 0-360
        val numOfPrizes = prizes.size // quantity of prize
        val degreesPerPrize = 360/numOfPrizes // size of sector per prize in degrees
        val shift = 0 // shit where the arrow points
        val prizeIndex = (shift + end) % numOfPrizes

        prizeText = "Prize is : ${prizes[prizeIndex]}"
        data=prizes[prizeIndex]
        val rotateAnim = RotateAnimation(
            0f,mSpinRevolution + end,
            Animation.RELATIVE_TO_SELF,
            0.5f, Animation.RELATIVE_TO_SELF,0.5f
        )
        rotateAnim.interpolator = DecelerateInterpolator()
        rotateAnim.repeatCount = 0
        rotateAnim.duration = mSpinDuration
        rotateAnim.setAnimationListener(this)
        rotateAnim.fillAfter = true
        pointerImageView!!.startAnimation(rotateAnim)

    }

    override fun onAnimationStart(p0: Animation?) {
        infoText!!.text = "Spinning..."
    }

    override fun onAnimationEnd(p0: Animation?) {
        infoText!!.text = prizeText

        val random = Random()
        val color =
            Color.argb(
                255, random.nextInt(256),
                random.nextInt(256), random.nextInt(256)
            )

        viewModels.insertToResult(Result(prizeText,data ,getCurrentTime(),color))


    }

    override fun onAnimationRepeat(p0: Animation?) {}

    private inner class PowerTouchListener: View.OnTouchListener {
        override fun onTouch(p0: View?, motionEvent: MotionEvent?): Boolean {

            when(motionEvent!!.action){
                MotionEvent.ACTION_DOWN ->{
                    flag = true
                    count = 0
                    Thread{
                        while (flag){
                            count++
                            if (count == 100){
                                try {
                                    Thread.sleep(100)
                                }catch (e: InterruptedException) {
                                    e.printStackTrace()
                                }
                                count = 0
                            }
                            try {
                                Thread.sleep(10)
                            }
                            catch (e: InterruptedException){
                                e.printStackTrace()
                            }
                        }
                    }.start()
                    return true
                }
                MotionEvent.ACTION_UP ->{
                    flag = false
                    startSpinner()
                    return false
                }

            }


            return false
        }

    }


}