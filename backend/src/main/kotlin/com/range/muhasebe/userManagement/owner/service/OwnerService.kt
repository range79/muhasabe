package com.range.muhasebe.userManagement.owner.service

import com.range.muhasebe.userManagement.owner.dto.WorkerAddRequest
import com.range.muhasebe.userManagement.owner.dto.WorkerResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


interface OwnerService {

    fun createWorker(workerAddRequest: WorkerAddRequest)
    fun deleteWorker(workerId: Long)
    fun listAllWorkers(pageable: Pageable): Page<WorkerResponse>
    fun restoreWorker(workerAddRequest: WorkerAddRequest)
}