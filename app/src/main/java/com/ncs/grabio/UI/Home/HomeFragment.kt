package com.ncs.grabio.UI.Home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ncs.grabio.HelperClasses.BounceEdgeEffectFactory
import com.ncs.grabio.R
import com.ncs.grabio.UI.Home.Adapter.DHolder
import com.ncs.grabio.UI.Home.Adapter.RecyclerViewAdapter
import com.ncs.grabio.UI.MainHostActivity
import com.ncs.grabio.UI.Profile.ProfileActivity
import com.ncs.grabio.databinding.FragmentHomeBinding
import org.greenrobot.eventbus.EventBus


class HomeFragment : Fragment(), View.OnClickListener, RecyclerViewAdapter.Callback {

    companion object {
        fun newInstance() = HomeFragment()
    }


    private lateinit var viewModel: HomeViewModel
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val eventbus : EventBus = EventBus.getDefault()
    private lateinit var progressCallback: ProgressCallback
    private lateinit var  recyclerView : RecyclerView

    lateinit var animBlink: Animation
    private var firstVisibleInListview: Int = -1;

    interface ProgressCallback{
        fun progressVisible(show: Int)
        fun progressShowToast(text: String, time:Long)
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        progressCallback = context as ProgressCallback
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        animBlink = AnimationUtils.loadAnimation(context,R.anim.blinkinf)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

//        Handler(Looper.getMainLooper()).postDelayed({
//            eventbus.post(DataHolder.progData(1,"Just wait and watch \uD83D\uDC40",2000))
//        }, 500)

        binding.gioActionbar.profileBtn.setOnClickListener(this)
        setRecyclerview()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(p0: View?) {
       when(p0) {
           binding.gioActionbar.profileBtn ->
               startActivity(Intent(activity,ProfileActivity::class.java))

       }
    }


    fun setRecyclerview(){

        val dataList = ArrayList<DHolder.Data>()
        dataList.add(DHolder.Data(RecyclerViewAdapter.VIEW_TYPE_OPPORTUNITY, "2. Hi! I am in View 2"))
        dataList.add(DHolder.Data(RecyclerViewAdapter.VIEW_TYPE_SEARCH_BAR, "2. Hi! I am in View 2"))
        dataList.add(DHolder.Data(RecyclerViewAdapter.VIEW_TYPE_POSTER, "1. Hi! I am in View 1"))
        dataList.add(DHolder.Data(RecyclerViewAdapter.VIEW_TYPE_POSTER, "1. Hi! I am in View 1"))
        dataList.add(DHolder.Data(RecyclerViewAdapter.VIEW_TYPE_OBSERVE_LIST, "1. Hi! I am in View 1"))



        val adptr = RecyclerViewAdapter(requireContext(), dataList, this)
        recyclerView = binding.recyclerView
        val layoutM = LinearLayoutManager(context, RecyclerView.VERTICAL, false)


        recyclerView.apply {
            layoutManager = layoutM
            adapter = adptr
            edgeEffectFactory = BounceEdgeEffectFactory()

        }

        firstVisibleInListview =layoutM.findFirstVisibleItemPosition();



    }



    override fun searchBarClicked(item: View) {
        (activity as MainHostActivity).actionSearchFrag(item)
    }




//    fun fadeText(){
//        val fadeIn = AlphaAnimation(0.0f, 1.0f)
//        val fadeOut = AlphaAnimation(1.0f, 0.0f)
//        txtView.startAnimation(fadeIn)
//        txtView.startAnimation(fadeOut)
//        fadeIn.duration = 1200
//        fadeIn.fillAfter = true
//        fadeOut.duration = 1200
//        fadeOut.fillAfter = true
//        fadeOut.startOffset = 4200 + fadeIn.startOffset
//    }


}



