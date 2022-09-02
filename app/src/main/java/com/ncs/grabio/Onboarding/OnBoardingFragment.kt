package com.ncs.grabio.Onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.ncs.grabio.R
import com.ncs.grabio.databinding.FragmentOnBoardingBinding


/**
 * Created by Alok Ranjan on 30-08-2022
 * Project : NCS Grab.io
 * Description : This fragment creates new instances of fragments to be embedded in viewpager , assigns ARG_POSITION values
 *               to the passed position value from the adapter,
 *               bundleOf(ARG_POSITION to position) exactly means make a map and assign the value to position passed
 **/


class OnBoardingFragment : Fragment() {

    companion object {
        private const val ARG_POSITION = "ARG_POSITION"

        fun getInstance(position: Int) = OnBoardingFragment().apply {
            arguments = bundleOf(ARG_POSITION to position)
        }
    }

    private lateinit var binding : FragmentOnBoardingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = FragmentOnBoardingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            val position : Int = requireArguments().getInt(ARG_POSITION)
        val onBoardingTitles = requireContext().resources.getStringArray(R.array.titleArray)
        val onBoardingTexts = requireContext().resources.getStringArray(R.array.descArray)
        val onBoardingImages = getOnBoardAssetsLocation()

        with(binding){
            onBoardingImage.setImageResource(onBoardingImages[position])
            onBoardingTextTitle.text = onBoardingTitles[position]
            onBoardingTextMsg.text = onBoardingTexts[position]

        }

    }

    private fun getOnBoardAssetsLocation(): List<Int> {
        val onBoardAssets: MutableList<Int> = ArrayList()
        onBoardAssets.add(R.drawable.s1)
        onBoardAssets.add(R.drawable.s2)
        onBoardAssets.add(R.drawable.s3)
        return onBoardAssets
    }

}