package com.range.muhasebe.userManagement.worker.service

import com.range.muhasebe.userManagement.worker.dto.WorkerAddRequest
import com.range.muhasebe.userManagement.worker.dto.WorkerDetailedResponse
import com.range.muhasebe.userManagement.worker.dto.WorkerResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.util.UUID

interface WorkerManagementService {
    fun createWorker(workerAddRequest: WorkerAddRequest)
    fun deleteWorker(workerId: UUID)
    fun listAllWorkers(pageable: Pageable): Page<WorkerResponse>
    fun restoreWorker(workerId: UUID)
    fun getDeletedWorkerDetailed(workerId: UUID): WorkerDetailedResponse
    fun getDeletedWorkers(pageable: Pageable): Page<WorkerResponse>
}