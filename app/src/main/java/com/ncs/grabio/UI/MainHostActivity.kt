package com.ncs.grabio.UI

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ncs.grabio.R
import com.ncs.grabio.SearchPage.SearchActivity
import com.ncs.grabio.UI.Home.Adapter.RecyclerViewAdapter
import com.ncs.grabio.UI.Home.HomeFragment
import com.ncs.grabio.databinding.ActivityMainBinding
import com.ncs.grabio.databinding.GrablinearprogressbarBinding
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode



class MainHostActivity : FragmentActivity(), HomeFragment.ProgressCallback{

    private  var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    lateinit var animFadeIn: Animation
    lateinit var animFadeOut: Animation
    private lateinit var progressView : GrablinearprogressbarBinding
    lateinit var bottmNav: BottomNavigationView
    lateinit var navController : NavController
    var selectedItem = -1

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

        //inits
        animFadeIn = AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_slide_in_bottom)
        animFadeOut = AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_slide_out_bottom)
        progressView = binding.progressInclude
        //bottom bar
        setBottomNavBar()




    }

    fun setBottomNavBar() {

         bottmNav = binding.bottomNav
         navController = findNavController(R.id.nav_host_fragment_activity_main)
        bottmNav.setupWithNavController(navController)


//        bottmNav.setOnItemSelectedListener{ item ->
//            selectFragment(item)
//            return@setOnItemSelectedListener false
//        }

    }

    public fun actionSearchFrag(item : View){
        val intent = Intent(this, SearchActivity::class.java)
        val options : ActivityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,item,
            ViewCompat.getTransitionName(item).toString()
        )
        startActivity(intent,options.toBundle())
    }



    fun selectFragment(item:MenuItem){

        if (selectedItem == -1)
            navController.navigate(item.itemId)
        else
            navController.navigate(
                when (item.itemId) {
                    R.id.home_item ->
                        if (selectedItem == R.id.opportunities_item)
                            R.id.action_opportunities_item_to_home_item2
                        else if (selectedItem == R.id.questionhub_item)
                            R.id.action_questionhub_item_to_home_item
                        else if (selectedItem == R.id.home_item)
                            item.itemId
                        else R.id.action_bloghub_item_to_home_item

                    R.id.opportunities_item ->
                        if (selectedItem == R.id.home_item)
                            R.id.action_home_item_to_opportunities_item2
                        else if (selectedItem == R.id.questionhub_item)
                            R.id.action_questionhub_item_to_opportunities_item
                        else if (selectedItem == R.id.opportunities_item)
                            item.itemId
                        else R.id.action_bloghub_item_to_opportunities_item


                    R.id.questionhub_item ->
                        if (selectedItem == R.id.home_item)
                            R.id.action_home_item_to_questionhub_item
                        else if (selectedItem == R.id.opportunities_item)
                            R.id.action_opportunities_item_to_questionhub_item
                        else if (selectedItem == R.id.questionhub_item)
                            item.itemId
                        else R.id.action_bloghub_item_to_questionhub_item


                    R.id.bloghub_item ->
                        if (selectedItem == R.id.home_item)
                            R.id.action_home_item_to_bloghub_item
                        else if (selectedItem == R.id.opportunities_item)
                            R.id.action_opportunities_item_to_bloghub_item
                        else if (selectedItem == R.id.bloghub_item)
                            item.itemId
                        else R.id.action_questionhub_item_to_bloghub_item

                    else -> item.itemId
                })

        selectedItem = item.itemId
        navController.saveState()


            // uncheck the other items.
            for (i in 0 until bottmNav.menu.size()) {
                val menuItem = bottmNav.menu.getItem(i)
                if (menuItem.itemId == item.itemId) menuItem.isChecked = true
            }



    }

    fun NavController.safeNavigate(direction: NavDirections) {
        Log.d("clickTag", "Click happened")
        currentDestination?.getAction(direction.actionId)?.run {
            Log.d("clickTag", "Click Propagated")
            navigate(direction)
        }
    }


    override fun progressVisible(show: Int) {
        if(show==1){
            progressView.linearProgressIndicator.visibility = View.VISIBLE
            progressView.linearProgressIndicator.startAnimation(animFadeIn)

        }else {
            progressView.linearProgressIndicator.startAnimation(animFadeOut)
            binding.progressInclude.linearProgressIndicator.visibility = View.GONE
            }

    }


    override fun progressShowToast(text: String, time: Long) {

        with(progressView.toast){
            setText(text)
            visibility = View.VISIBLE
            startAnimation(animFadeIn)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            progressView.toast.startAnimation(animFadeOut)
        },time)

        Handler(Looper.getMainLooper()).postDelayed({
            with(progressView.toast){
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
             //   progressShowToast(progData.text,progData.time)
            },2000)
        }
    }



}