package com.range.muhasebe.userManagement.worker.service

import com.range.muhasebe.userManagement.user.domain.model.User
import com.range.muhasebe.userManagement.worker.domain.model.Worker
import com.range.muhasebe.userManagement.worker.dto.WorkerEditRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface WorkerService {
    fun getWorkersByOwnerId(pageable: Pageable,userId: Long): Page<Worker>
    fun editProfile(workerEditRequest: WorkerEditRequest)
    fun getMyProfile(): Worker
}