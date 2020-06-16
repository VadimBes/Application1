package com.art_int_labs.lead_up.ui.Course.detail

import androidx.lifecycle.ViewModel
import com.art_int_labs.lead_up.data.repository.MainRepository
import com.art_int_labs.lead_up.internal.lazyDeferred

class CourseDetailViewModel(
    val mainRepository: MainRepository
) : ViewModel() {
    var id:Int = 2

    val lessons by lazyDeferred {
        mainRepository.getLessons(id)
    }
}
