package com.range.muhasebe.userManagement.worker.service.impl

import com.range.muhasebe.userManagement.worker.domain.model.Worker
import com.range.muhasebe.userManagement.worker.domain.repository.WorkerRepository
import com.range.muhasebe.userManagement.worker.dto.WorkerEditRequest
import com.range.muhasebe.userManagement.worker.service.WorkerService
import org.springframework.stereotype.Service

@Service
class WorkerServiceImpl(
    private val workerRepository: WorkerRepository,
): WorkerService {


    override fun editProfile(workerEditRequest: WorkerEditRequest) {
        TODO()
    }

    override fun getMyProfile(): Worker {
        TODO()
    }


    }



