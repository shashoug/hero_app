package shashoug.com.tracker_domain.use_case

import kotlinx.coroutines.flow.Flow
import shashoug.com.tracker_domain.model.TrackedFood
import shashoug.com.tracker_domain.repository.TrackerRepository
import java.time.LocalDate

class GetFoodForDate(
    private val repository: TrackerRepository
) {
     operator fun  invoke(date: LocalDate) : Flow<List<TrackedFood>>{
        return  repository.getFoodsForDate(date)
    }
}