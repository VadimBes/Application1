package com.art_int_labs.lead_up.ui.Course.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.data.db.entity.lesson.Lesson
import com.art_int_labs.lead_up.data.db.entity.main.Course
import com.art_int_labs.lead_up.data.network.ApiLeadApiServices
import com.art_int_labs.lead_up.internal.IdNotFoundExeption
import com.art_int_labs.lead_up.ui.Course.CourseFragSimpleItem
import com.art_int_labs.lead_up.ui.base.ScopeFragment
import com.bumptech.glide.Glide
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.course_detail_item.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class CourseDetailFragment : ScopeFragment(),KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory by instance<CourseDetailViewModelFactory>()
    private val apiLeadApiServices by instance<ApiLeadApiServices>()

    private lateinit var viewModel: CourseDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.course_detail_item, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = arguments?.let { CourseDetailFragmentArgs.fromBundle(it) }
        val id = safeArgs?.id?:throw IdNotFoundExeption()
        val header = safeArgs?.header
        val picture = safeArgs?.picture
        viewModel = ViewModelProviders.of(this,viewModelFactory).get(CourseDetailViewModel::class.java)
        info_course.movementMethod = ScrollingMovementMethod()
        viewModel.id = id
        updateImage(picture)
        updateTitle(header)
        bindUI()
    }

    private fun bindUI() = launch {
        val lessons = viewModel.lessons.await()

        lessons.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            initRecyclerView(it.filterLesson())
        })
    }

    private fun List<Lesson>.filterLesson():List<CourseDetailSimpleItem>{
        val list = mutableListOf<CourseDetailSimpleItem>()
        forEach {
            val item = CourseDetailSimpleItem(it)
            list.add(item)
        }
        return list
    }

    private fun initRecyclerView(items:List<CourseDetailSimpleItem>){
        val groupAdapter = GroupAdapter<GroupieViewHolder>().apply {
            addAll(items)
        }

        
        recycler_lesson.apply {
            adapter = groupAdapter
            layoutManager = LinearLayoutManager(this@CourseDetailFragment.context)
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as CourseDetailSimpleItem)?.let {
                showLesson(it.lesson.long_text,it.lesson.video,view)
            }
        }

    }

    private fun updateImage(picture:String){
        Glide.with(this).load(picture).into(icon_course)
    }
    private fun updateTitle(title:String){
        name_course.text = title
    }

    private fun updateInfo(info:String){
        name_course.text = info
    }

    private fun showLesson(info: String,video:String?,view: View){
        val videoinfo = video?:"none"
        val actionDetail = CourseDetailFragmentDirections.actionDetail(info,videoinfo)
        Navigation.findNavController(view).navigate(actionDetail)
    }


}
