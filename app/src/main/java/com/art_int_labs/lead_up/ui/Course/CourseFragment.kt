package com.art_int_labs.lead_up.ui.Course

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.data.db.entity.main.Course
import com.art_int_labs.lead_up.ui.base.ScopeFragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.course_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CourseFragment : ScopeFragment(),KodeinAware {
    override val kodein by closestKodein()
    val courseViewModelFactory by instance<CourseViewModelFactory>()

    private lateinit var viewModel: CourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.course_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, courseViewModelFactory).get(CourseViewModel::class.java)
        bindUI()
    }

    fun bindUI() = launch {
        val currentcourse = viewModel.course.await()
        currentcourse.observe(viewLifecycleOwner, Observer {
            if (it ==null) return@Observer
            initRecyclerView(it.filterCourse())
            progress_course.visibility = View.GONE

        })
    }

    private fun List<Course>.filterCourse():List<CourseFragSimpleItem>{
        val list = mutableListOf<CourseFragSimpleItem>()
        forEach {
           val item =CourseFragSimpleItem(it)
            list.add(item)
        }
        return list
    }

    private fun initRecyclerView(items:List<CourseFragSimpleItem>){
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        recyclerCourse.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(this@CourseFragment.context)
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as CourseFragSimpleItem)?.let {
                showLesson(it.course.id,it.course.heading,it.course.picture,view)
            }
        }
    }

    private fun showLesson(id:Int,header:String,picture:String,view: View){
        val actionDetail = CourseFragmentDirections.actionDetail(id,header,picture)
        Navigation.findNavController(view).navigate(actionDetail)
    }
}




