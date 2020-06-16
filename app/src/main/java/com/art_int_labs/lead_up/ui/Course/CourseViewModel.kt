package com.art_int_labs.lead_up.ui.Course

import androidx.lifecycle.ViewModel
import com.art_int_labs.lead_up.data.repository.MainRepository
import com.art_int_labs.lead_up.internal.lazyDeferred

class CourseViewModel(
    val mainRepository: MainRepository
) : ViewModel() {
    val course by lazyDeferred {
        mainRepository.getCurrentCourses()
    }
}
