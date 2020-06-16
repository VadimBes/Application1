package com.art_int_labs.lead_up.ui.Main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer

import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.data.db.entity.main.ListNews
import com.art_int_labs.lead_up.internal.CustomTransformer
import com.art_int_labs.lead_up.ui.base.ScopeFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.main_fragment_page.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class MainFragment : ScopeFragment(),KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory by instance<MainViewModelFactory>()

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(MainViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch {
        val currentNews = viewModel.news.await()
        currentNews.observe(this@MainFragment, Observer {
            if (it ==null) return@Observer
            initViewPager(it.toNews())
            pagerBar.visibility = View.GONE
            initRecyclerView(it.toCourse())
            recyclerBar.visibility = View.GONE
        })
    }

    private fun ListNews.toNews():List<NewsSimpleItem>{
        val list = mutableListOf<NewsSimpleItem>()
        this.news.forEach {
            val new = NewsSimpleItem(it)
            list.add(new)
        }
        return list
    }

    private fun ListNews.toCourse():List<CourseSimpleItem>{
        val list = mutableListOf<CourseSimpleItem>()
        this.courses.forEach {
            val course = CourseSimpleItem(it)
            list.add(course)
        }
        return list
    }

    private fun initViewPager(items:List<NewsSimpleItem>){
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }
        newsViewPager.apply {
            adapter = groupAdapter
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER)
        }
        newsViewPager.setPageTransformer(CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer(CustomTransformer())
        })
    }

    private fun initRecyclerView(items:List<CourseSimpleItem>){
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        suggestedCoursesRecycler.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(this@MainFragment.context,LinearLayoutManager.HORIZONTAL,false)
        }
    }


}
