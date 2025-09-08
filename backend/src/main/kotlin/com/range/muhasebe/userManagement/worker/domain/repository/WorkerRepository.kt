package com.range.muhasebe.userManagement.worker.domain.repository

import com.range.muhasebe.userManagement.worker.domain.model.Worker
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository


interface WorkerRepository: JpaRepository<Worker, Long> {
    fun findWorkerByOwnerId(ownerId: Long,pageable: Pageable): Page<Worker>
}