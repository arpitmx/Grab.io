package com.ncs.grabio.UI.AskHub

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ncs.grabio.R

class AskHubFragment : Fragment() {

    companion object {
        fun newInstance() = AskHubFragment()
    }

    private lateinit var viewModel: AskHubViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_question_hub, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AskHubViewModel::class.java)
        // TODO: Use the ViewModel
    }

}