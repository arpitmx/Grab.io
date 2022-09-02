package com.ncs.grabio.Onboarding

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


/**
 * Created by Alok Ranjan on 30-08-2022
 * Project : NCS Grab.io
 * Description : This class wires the viewPager widget to activity which holds
     viewPagers (The slider which will hold our fragments)
     - takes two parameters Hosting Activity and Number of items in the view Pager
     - Creates new fragment at n th position through createFragment(position :Int) method like
       fragment1 with details1(Title, desc, imgSrc) @pos 1 , fragment2 with details2 @pos 2, fragment3 with details3 @pos 3
 **/

class OnBoardingAdapter(activity: OnBoardingActivity, private val itemCount : Int) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return itemCount
    }

    override fun createFragment(position: Int): Fragment {
        return OnBoardingFragment.getInstance(position)
    }




}