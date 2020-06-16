package com.art_int_labs.lead_up.ui.Knowledge.detail

import androidx.lifecycle.ViewModel
import com.art_int_labs.lead_up.data.repository.MainRepository
import com.art_int_labs.lead_up.internal.lazyDeferred

class KnowlegeDetailViewModel(
    val mainRepository: MainRepository
) : ViewModel() {
    var id = 1
    val products by lazyDeferred {
        mainRepository.getProducts(id)
    }
}
