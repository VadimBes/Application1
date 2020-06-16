package com.art_int_labs.lead_up.ui.Knowledge.detail.product

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.ui.base.ScopeFragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.product_fragment.*

class ProductFragment : ScopeFragment() {


    private lateinit var viewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        val safeArgs = arguments?.let { ProductFragmentArgs.fromBundle(it) }
        info_product.movementMethod = ScrollingMovementMethod()
        updateImage(safeArgs!!.picture)
        updateTitle(safeArgs.header)
        //updateInfo(safeArgs.longText)
    }

    private fun updateImage(picture:String){
        Glide.with(this).load(picture).into(icon_product)
    }
    private fun updateTitle(title:String){
        name_product.text = title
    }

    private fun updateInfo(info:String){
        info_product.text = info
    }

}
