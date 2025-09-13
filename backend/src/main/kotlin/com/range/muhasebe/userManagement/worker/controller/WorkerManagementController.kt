package com.range.muhasebe.userManagement.worker.controller

import com.range.muhasebe.userManagement.worker.dto.WorkerAddRequest
import com.range.muhasebe.userManagement.worker.dto.WorkerDetailedResponse
import com.range.muhasebe.userManagement.worker.dto.WorkerResponse
import com.range.muhasebe.userManagement.worker.api.WorkerManagerApi
import com.range.muhasebe.userManagement.worker.service.WorkerManagementService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class WorkerManagementController(
    private val workerManagerService: WorkerManagementService
): WorkerManagerApi {
    override fun createWorker(workerAddRequest: WorkerAddRequest) {
        return workerManagerService.createWorker(workerAddRequest)
    }

    override fun deleteWorker(workerId: UUID) {
        return workerManagerService.deleteWorker(workerId)
    }

    override fun listAllWorkers(pageable: Pageable): Page<WorkerResponse> {
        return workerManagerService.listAllWorkers(pageable)
    }

    override fun restoreWorker(workerId: UUID) {
        return workerManagerService.restoreWorker(workerId)
    }

    override fun getDeletedWorkerDetailed(workerId: UUID): WorkerDetailedResponse {
        return workerManagerService.getDeletedWorkerDetailed(workerId)
    }

    override fun getDeletedWorkers(pageable: Pageable): Page<WorkerResponse> {
        return workerManagerService.getDeletedWorkers(pageable)
    }
}