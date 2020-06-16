package com.art_int_labs.lead_up.ui.Knowledge.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.data.db.entity.knowledge.detail.Product
import com.art_int_labs.lead_up.data.network.ApiLeadApiServices
import com.art_int_labs.lead_up.internal.GridSpacingItemDecoration
import com.art_int_labs.lead_up.internal.ProductGridItemDecoration
import com.art_int_labs.lead_up.ui.Course.detail.CourseDetailSimpleItem
import com.art_int_labs.lead_up.ui.base.ScopeFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.course_detail_item.*
import kotlinx.android.synthetic.main.knowledge_detail_item.*
import kotlinx.android.synthetic.main.knowlegge_detail_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class KnowlegeDetailFragment : ScopeFragment(),KodeinAware {

    override val kodein by closestKodein()
    private val apiLeadApiServices by instance<ApiLeadApiServices>()
    val viewModelFactory by instance<KnowledgeDetailViewModelFactory>()
    private lateinit var viewModel: KnowlegeDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.knowlegge_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(KnowlegeDetailViewModel::class.java)
        val safeArgs = arguments?.let{ KnowlegeDetailFragmentArgs.fromBundle(it)}
        val id = safeArgs?.id?:1
        viewModel.id = id
        bindUI()
    }

    fun bindUI() = launch {
        val product = viewModel.products.await()
        product.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            initRecyclerView(it.filterProduct())
        })
    }

    private fun List<Product>.filterProduct():List<KnowledgeDetailSimpleItem>{
        val list = mutableListOf<KnowledgeDetailSimpleItem>()
        forEach {
            val item = KnowledgeDetailSimpleItem(it)
            list.add(item)
        }
        return list
    }

    private fun initRecyclerView(items:List<KnowledgeDetailSimpleItem>){
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        val largePadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing)
        val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small)
        knowledge_detail_recycler.apply {
            adapter = groupAdapter
            layoutManager = GridLayoutManager(this@KnowlegeDetailFragment.context,2,RecyclerView.VERTICAL,false)
            addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as KnowledgeDetailSimpleItem)?.let {
                showDetail(it.product.heading,it.product.long_text,it.product.picture,view)
            }
        }

    }

    fun showDetail(header:String,long_text:String,picture:String,view: View){
        val actionDetail = KnowlegeDetailFragmentDirections.actionProduct(header,long_text,picture)
        Navigation.findNavController(view).navigate(actionDetail)
    }

}
