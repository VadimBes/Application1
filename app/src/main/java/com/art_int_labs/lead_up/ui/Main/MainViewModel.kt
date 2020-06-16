package com.art_int_labs.lead_up.ui.Main

import androidx.lifecycle.ViewModel
import com.art_int_labs.lead_up.data.repository.MainRepository
import com.art_int_labs.lead_up.internal.lazyDeferred

class MainViewModel(
    val mainRepository: MainRepository
) : ViewModel() {
    val news by lazyDeferred {
        mainRepository.getNews()
    }
}
