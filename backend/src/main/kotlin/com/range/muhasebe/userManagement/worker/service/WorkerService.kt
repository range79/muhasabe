package com.range.muhasebe.userManagement.worker.service

import com.range.muhasebe.userManagement.worker.domain.model.Worker
import com.range.muhasebe.userManagement.worker.dto.WorkerEditRequest

interface WorkerService {

    fun editProfile(workerEditRequest: WorkerEditRequest)
    fun getMyProfile(): Worker


}