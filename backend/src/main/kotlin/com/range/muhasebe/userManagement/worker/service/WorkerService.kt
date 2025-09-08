package com.range.muhasebe.userManagement.worker.service

import com.range.muhasebe.userManagement.owner.dto.WorkerDetailedResponse
import com.range.muhasebe.userManagement.owner.dto.WorkerResponse
import com.range.muhasebe.userManagement.worker.domain.model.Worker
import com.range.muhasebe.userManagement.worker.dto.WorkerEditRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface WorkerService {

    fun editProfile(workerEditRequest: WorkerEditRequest)
    fun getMyProfile(): Worker


}