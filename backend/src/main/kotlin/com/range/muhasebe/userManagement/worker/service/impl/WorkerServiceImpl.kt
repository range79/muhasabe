package com.range.muhasebe.userManagement.worker.service.impl

import com.range.muhasebe.userManagement.worker.domain.model.Worker
import com.range.muhasebe.userManagement.worker.domain.repository.WorkerRepository
import com.range.muhasebe.userManagement.worker.dto.WorkerEditRequest
import com.range.muhasebe.userManagement.worker.service.WorkerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

class WorkerServiceImpl(
    private val workerRepository: WorkerRepository,
): WorkerService {
    override fun getWorkersByOwnerId(pageable: Pageable,userId: Long): Page<Worker> {
        return workerRepository.findWorkerByOwnerId(userId,pageable)
    }

    override fun editProfile(workerEditRequest: WorkerEditRequest) {

    }
}