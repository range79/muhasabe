package com.range.muhasebe.userManagement.owner.service

import com.range.muhasebe.userManagement.owner.dto.WorkerAddRequest
import com.range.muhasebe.userManagement.owner.dto.WorkerResponse
import org.springframework.data.domain.Page


interface OwnerService {

    fun createWorker(workerAddRequest: WorkerAddRequest)
    fun deleteWorker(workerAddRequest: WorkerAddRequest)
    fun listAllWorkers(): Page<WorkerResponse>
    fun restoreWorker(workerAddRequest: WorkerAddRequest)
}