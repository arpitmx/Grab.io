package com.ncs.grabio.UI.Grabhub

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ncs.grabio.R

class GrabhubFragment : Fragment() {

    companion object {
        fun newInstance() = GrabhubFragment()
    }

    private lateinit var viewModel: GrabhubViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_opportunities, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GrabhubViewModel::class.java)
        // TODO: Use the ViewModel
    }

}