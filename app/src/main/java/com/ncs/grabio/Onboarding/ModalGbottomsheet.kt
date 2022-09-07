package com.ncs.grabio.Onboarding

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.ncs.grabio.UI.MainActivity
import com.ncs.grabio.R
import com.ncs.grabio.databinding.GoogleauthbottomsheetBinding


/**
 * Created by Alok Ranjan on 01-09-2022
 * Project : NCS Grab.io
 * Description :
 * Github : https://github.com/arpitmx
 **/
class ModalGbottomsheet : BottomSheetDialogFragment() {


    lateinit var binding : GoogleauthbottomsheetBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = GoogleauthbottomsheetBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val oauthbtn = binding.oauthbtn

        oauthbtn.setOnClickListener{
        startAuthProcess(it)
        }


    }

    fun startAuthProcess(view: View){

        view.animation = AnimationUtils.loadAnimation(activity,R.anim.fadeout)
        view.visibility = View.GONE
        binding.authProg.visibility = View.VISIBLE
        this.isCancelable= false
        Handler(Looper.getMainLooper()).postDelayed({

            startActivity(Intent(activity, MainActivity::class.java))
            requireActivity().finish()
        },500)
    }


    companion object {
        const val TAG = "ModalBottomSheet"
    }

}

