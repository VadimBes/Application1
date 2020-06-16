package com.art_int_labs.lead_up.ui.Course.detail.lesson

import android.opengl.Visibility
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.art_int_labs.lead_up.R
import com.art_int_labs.lead_up.ui.Course.detail.CourseDetailFragmentArgs
import kotlinx.android.synthetic.main.youtube_player_example.*

class LessonFragment : Fragment() {

    companion object {
        fun newInstance() = LessonFragment()
    }

    private lateinit var viewModel: LessonViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.youtube_player_example, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(LessonViewModel::class.java)
        val safeArgs = arguments?.let { LessonFragmentArgs.fromBundle(it) }
        val info = safeArgs?.info
        val video = safeArgs?.video
        if (video=="none"){
            lesson_video.visibility = View.GONE
            lesson_text.text = info
        }else{
            lesson_text.visibility = View.GONE
        }
    }

}
