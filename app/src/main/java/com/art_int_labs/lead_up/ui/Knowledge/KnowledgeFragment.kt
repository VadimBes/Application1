package com.art_int_labs.lead_up.ui.Knowledge

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.data.db.entity.knowledge.Knowledge
import com.art_int_labs.lead_up.data.network.ApiLeadApiServices
import com.art_int_labs.lead_up.ui.Course.CourseFragSimpleItem
import com.art_int_labs.lead_up.ui.base.ScopeFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.course_fragment.*
import kotlinx.android.synthetic.main.knowledge_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class KnowledgeFragment : ScopeFragment(),KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory by instance<KnowledgeViewModelFactory>()
    private val apiLeadApiServices by instance<ApiLeadApiServices>()

    private lateinit var viewModel: KnowledgeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.knowledge_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(KnowledgeViewModel::class.java)
        bindUI()

    }

    private fun bindUI()=launch {
        val currentknowledge = viewModel.knowledge.await()
        currentknowledge.observe(viewLifecycleOwner, Observer {
            if (it==null) return@Observer
            initRecyclerView(it.filterKnow())

        })
    }

    private fun List<Knowledge>.filterKnow():List<KnowledgeSimpleItem>{
        val list = mutableListOf<KnowledgeSimpleItem>()
        forEach {
            val item = KnowledgeSimpleItem(it)
            list.add(item)
        }
        return list
    }

    private fun initRecyclerView(items:List<KnowledgeSimpleItem>){
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        knowledgeRecycler.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(this@KnowledgeFragment.context)
        }

        groupAdapter.setOnItemClickListener{item, view ->
            (item as KnowledgeSimpleItem)?.let {
                showDetail(it.knowledge.id,view)
            }
        }

    }

    fun showDetail(id:Int,view: View){
        val actionDetail = KnowledgeFragmentDirections.actionKnowledge(id)
        Navigation.findNavController(view).navigate(actionDetail)
    }

}
