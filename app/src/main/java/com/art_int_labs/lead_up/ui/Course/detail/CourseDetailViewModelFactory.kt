package com.art_int_labs.lead_up.ui.Course.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.art_int_labs.lead_up.data.repository.MainRepository


class CourseDetailViewModelFactory(
    private val mainRepository: MainRepository
):ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CourseDetailViewModel(
            mainRepository
        ) as T
    }
}