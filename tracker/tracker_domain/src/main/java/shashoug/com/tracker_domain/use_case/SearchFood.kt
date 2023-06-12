package shashoug.com.tracker_domain.use_case

import shashoug.com.tracker_domain.model.TrackableFood
import shashoug.com.tracker_domain.repository.TrackerRepository

class SearchFood(
    private val repository: TrackerRepository
) {
    suspend operator fun  invoke(
        query: String,
        page : Int = 1,
        pageSize : Int = 40
    ) : Result<List<TrackableFood>>{
        if(query.isBlank()){
            return  Result.success(emptyList())
        }
        return  repository.searchFood(query = query.trim(),page = page,pageSize = pageSize)
    }

}