package com.art_int_labs.lead_up.ui.Achivments

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.SparseArray
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.ui.base.ScopeFragment
import kotlinx.android.synthetic.main.achivments_fragment.*
import kotlinx.coroutines.launch

class AchivmentsFragment : ScopeFragment() {

    private lateinit var viewModel: AchivmentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.achivments_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AchivmentsViewModel::class.java)


    }





}
