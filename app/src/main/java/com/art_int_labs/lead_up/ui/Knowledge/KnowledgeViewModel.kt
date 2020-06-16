package com.art_int_labs.lead_up.ui.Knowledge

import androidx.lifecycle.ViewModel
import com.art_int_labs.lead_up.data.repository.MainRepository
import com.art_int_labs.lead_up.internal.lazyDeferred

class KnowledgeViewModel(
    val mainRepository: MainRepository
) : ViewModel() {
    val knowledge by lazyDeferred {
        mainRepository.getCurrentKnowledges()
    }
}
