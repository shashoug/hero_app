package shashoug.com.tracker_presentation.search

import shashoug.com.tracker_domain.model.TrackableFood

data class TrackableFoodUiState(
    val food : TrackableFood,
    val isExpanded : Boolean = false,
    val amount : String = ""
)
