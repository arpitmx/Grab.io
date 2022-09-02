package com.ncs.grabio.Onboarding;

import android.content.Intent
import android.graphics.Color
import android.icu.lang.UCharacter
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

import com.ncs.grabio.R;
import com.ncs.grabio.databinding.ActivityMainBinding;
import com.ncs.grabio.databinding.GoogleauthbottomsheetBinding

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
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val numberOfScreens = resources.getStringArray(R.array.titleArray).size
        binding.onBoardingMainContainer.makeStatusBarTransparent()
        val onBoardingAdapter = OnBoardingAdapter(this, numberOfScreens)
        binding.onBoardingViewPager.adapter = onBoardingAdapter
        binding.onBoardingViewPager.registerOnPageChangeCallback(onBoardingPageChangeCallback)
        binding.onBoardingViewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        binding.onBoardingViewPager.offscreenPageLimit = 3

    }

    private fun updateCircleMarker(binding: ActivityMainBinding, position: Int) {

        binding.btnNext.startAnimation(AnimationUtils.loadAnimation(this,R.anim.blink))
        when (position) {
            0 -> {

                binding.b1.setColorFilter(ContextCompat.getColor(this,R.color.s1bg))
                binding.b2.setColorFilter(R.color.DarkBgGray)
                binding.b3.setColorFilter(Color.DKGRAY)
                binding.btnNext.setText("Slide down")
                binding.btnNext.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,R.drawable.ic_baseline_keyboard_arrow_down_24,0)
                binding.btnNext.setBackgroundResource(R.drawable.on1)
                binding.btnNext.setOnClickListener {
                    binding.onBoardingViewPager.setCurrentItem(1,true)

                }
            }
            1 -> {
                binding.b1.setColorFilter(Color.DKGRAY)
                binding.b2.setColorFilter(ContextCompat.getColor(this, R.color.s2bg))
                binding.b3.setColorFilter(R.color.DarkBgGray)
                binding.btnNext.setText("Slide down")
                binding.btnNext.setBackgroundColor(ContextCompat.getColor(this, R.color.s2bg))
                binding.btnNext.setBackgroundResource(R.drawable.on2)
                binding.btnNext.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,R.drawable.ic_baseline_keyboard_arrow_down_24,0)


                binding.btnNext.setOnClickListener {
                    binding.onBoardingViewPager.setCurrentItem(2,true)
                }
            }
            2 -> {

                binding.b1.setColorFilter(Color.DKGRAY)
                binding.b2.setColorFilter(Color.DKGRAY)
                binding.b3.setColorFilter(ContextCompat.getColor(this, R.color.s3bg))
                binding.btnNext.setText("Get Started")
                binding.btnNext.setBackgroundColor(ContextCompat.getColor(this, R.color.s3bg))
                binding.btnNext.setBackgroundResource(R.drawable.on3)
                binding.btnNext.startAnimation(AnimationUtils.loadAnimation(this,R.anim.blinkinf))
                binding.btnNext.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0,R.drawable.ic_baseline_offline_bolt_24,0)

                binding.btnNext.setOnClickListener{
                    binding.btnNext.animation.cancel()
                    showGBottomsheet()
                }

            }
        }
    }

    fun showGBottomsheet(){

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