package com.ncs.grabio.UI.Blogs

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ncs.grabio.R

class BlogsFragment : Fragment() {

    companion object {
        fun newInstance() = BlogsFragment()
    }

    private lateinit var viewModel: BlogsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_blogs, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BlogsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}