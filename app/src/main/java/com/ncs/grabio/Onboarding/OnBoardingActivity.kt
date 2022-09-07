package com.ncs.grabio.Onboarding;

import android.content.Intent
import android.graphics.Color
import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

import com.ncs.grabio.R;
import com.ncs.grabio.databinding.ActivityMainBinding;
import com.ncs.grabio.databinding.GoogleauthbottomsheetBinding
import com.ncs.grabio.databinding.OnboardingHostBinding
import kotlin.math.abs

/**
 * Created by Alok Ranjan on 30-08-2022
 * Project : NCS Grab.io
 * Description : This activity host the Viewpager and other components
 *        @ImpNote 1 :  Keep the static images into their specific drawable folder depending
 *        on the image resolution... drawable-hdpi, drawable-xhdpi ... images in these
 *        are optimized before displaying, increasing the rendering performance of viewpager
 *
 *        @ImpNote 2 :  viewpager.offscreenPageLimit = 3  loads the number of pages to
 *        preload  into cache memory , so these 3 screens will be preloaded instead of 1
 *        , when set to 1 , when swiped to next screen, it will be loaded at that time. (can make things jitter)
 *        Also note that settingOffScreen limit to high number can cause outOfMemory errors.

 *
 *
 **/

class OnBoardingActivity : AppCompatActivity() {

    private var onBoardingPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            updateCircleMarker(binding, position)
        }
    }
    private lateinit var binding: OnboardingHostBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OnboardingHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numberOfScreens = resources.getStringArray(R.array.titleArray).size
        binding.onBoardingMainContainer.makeStatusBarTransparent()
        val onBoardingAdapter = OnBoardingAdapter(this, numberOfScreens)


        with(binding.onBoardingViewPager) {

            adapter = onBoardingAdapter
            registerOnPageChangeCallback(onBoardingPageChangeCallback)
            orientation = ViewPager2.ORIENTATION_VERTICAL
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            setPageTransformer(ViewPager2PageTransformation())

        }

    }

    private fun updateCircleMarker(binding : OnboardingHostBinding, position: Int) {

       // binding.btnNext.startAnimation(AnimationUtils.loadAnimation(this,R.anim.blink))
        binding.btnNext.startAnimation(AnimationUtils.loadAnimation(baseContext,R.anim.blinkinf))

        when (position) {

            0 -> {

                binding.b1.setColorFilter(ContextCompat.getColor(this,R.color.s1bg))
                binding.b2.setColorFilter(R.color.DarkBgGray)
                binding.b3.setColorFilter(Color.DKGRAY)

                with(binding.btnNext){
                    setText("Slide down")
                    setBackgroundColor(ContextCompat.getColor( context, R.color.s1bg))
                    setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,R.drawable.ic_baseline_keyboard_arrow_down_24,0)
                    setBackgroundResource(R.drawable.on1)
                    setOnClickListener {
                        binding.onBoardingViewPager.setCurrentItem(1,true)

                    }
                }


            }
            1 -> {
                binding.b1.setColorFilter(Color.DKGRAY)
                binding.b2.setColorFilter(ContextCompat.getColor(this, R.color.s2bg))
                binding.b3.setColorFilter(R.color.DarkBgGray)

                with(binding.btnNext){
                    setText("Slide down")
                    setBackgroundColor(ContextCompat.getColor(context, R.color.s2bg))
                    setBackgroundResource(R.drawable.on2)
                    setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,R.drawable.ic_baseline_keyboard_arrow_down_24,0)
                    setOnClickListener {
                        binding.onBoardingViewPager.setCurrentItem(2,true)
                    }
                }


            }
            2 -> {

                binding.b1.setColorFilter(Color.DKGRAY)
                binding.b2.setColorFilter(Color.DKGRAY)
                binding.b3.setColorFilter(ContextCompat.getColor(baseContext, R.color.s3bg))

                with(binding.btnNext){
                    setText("Get Started")
                    setBackgroundColor(ContextCompat.getColor(context, R.color.s3bg))
                    setBackgroundResource(R.drawable.on3)
                    setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,R.drawable.ic_baseline_offline_bolt_24,0)

                    setOnClickListener{
                        binding.btnNext.animation.cancel()
                        showGBottomsheet()
                    }


                }


            }
        }
    }

    fun showGBottomsheet() {

        val modalBottomSheet = ModalGbottomsheet()
        modalBottomSheet.show(supportFragmentManager, ModalGbottomsheet.TAG)
    }

    override fun onDestroy() {
        binding.onBoardingViewPager.unregisterOnPageChangeCallback(onBoardingPageChangeCallback)
        super.onDestroy()
    }

    private fun View.makeStatusBarTransparent() {
        this.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }

     override fun onBackPressed() {
        super.onBackPressed()
        binding.onBoardingViewPager.setCurrentItem(0,true)

    }

}

class ViewPager2PageTransformation : ViewPager2.PageTransformer {
    override fun transformPage(page: View, position: Float) {
//        val absPos = Math.abs(position)
//        page.apply {
//
//            translationY = absPos * 500f
//            translationX = absPos * 500f
//
//            Log.d("TAG", "transformPage: "+ absPos)
//
//            scaleX = 1f
//            scaleY = 1f
//        }
        when {
            position < -1 ->
                page.alpha = 0.1f
            position <= 1 -> {
                page.alpha = Math.max(0.2f, 1 - Math.abs(position))
            }
            else -> page.alpha = 0.1f
        }
    }
}