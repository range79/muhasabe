package com.range.muhasebe.userManagement.worker.service

import com.range.muhasebe.userManagement.user.dto.WorkerAddRequest
import com.range.muhasebe.userManagement.user.dto.WorkerDetailedResponse
import com.range.muhasebe.userManagement.user.dto.WorkerResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface WorkerManagementService {
    fun createWorker(workerAddRequest: WorkerAddRequest)
    fun deleteWorker(workerId: Long)
    fun listAllWorkers(pageable: Pageable): Page<WorkerResponse>
    fun restoreWorker(workerId: Long)
    fun getDeletedWorkerDetailed(workerId: Long): WorkerDetailedResponse
    fun getDeletedWorkers(pageable: Pageable): Page<WorkerResponse>
}