package com.ncs.grabio.UI

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ncs.grabio.R
import com.ncs.grabio.UI.Home.HomeFragment
import com.ncs.grabio.databinding.ActivityMainBinding
import com.ncs.grabio.databinding.GrablinearprogressbarBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


class MainHostActivity : FragmentActivity(), HomeFragment.ProgressCallback {

    private  var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    lateinit var animFadeIn: Animation
    lateinit var animFadeOut: Animation
    private lateinit var pview : GrablinearprogressbarBinding


    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroy() {
        super.onDestroy()
      //  _binding = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pview = binding.progressInclude


        val navView: BottomNavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navView.setupWithNavController(navController)


        animFadeIn = AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_slide_in_bottom)
        animFadeOut = AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_slide_out_bottom)

    }


    override fun progressVisible(show: Int) {
        if(show==1){
            pview.linearProgressIndicator.visibility = View.VISIBLE
            pview.linearProgressIndicator.startAnimation(animFadeIn)

        }else {
            pview.linearProgressIndicator.startAnimation(animFadeOut)
            binding.progressInclude.linearProgressIndicator.visibility = View.GONE
            }

    }


    override fun progressShowToast(text: String, time: Long) {

        with(pview.toast){
            setText(text)
            visibility = View.VISIBLE
            startAnimation(animFadeIn)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            pview.toast.startAnimation(animFadeOut)
        },time)

        Handler(Looper.getMainLooper()).postDelayed({
            with(pview.toast){
                visibility = View.GONE
            }
        },time+400)


    }


    @Subscribe(threadMode = ThreadMode.MAIN)
      fun toggleProgressThenToast(progData : DataHolder.progData) {
        //Timber.d("Thread : "+Thread.currentThread())
        if (progData.show == 1){
            progressVisible(1)
            Handler(Looper.getMainLooper()).postDelayed({
                progressVisible(0)
                progressShowToast(progData.text,progData.time)
            },2000)
        }
    }


}