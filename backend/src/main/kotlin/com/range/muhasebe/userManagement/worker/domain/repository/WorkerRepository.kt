package com.range.muhasebe.userManagement.worker.domain.repository

import com.range.muhasebe.userManagement.worker.domain.model.Worker
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface WorkerRepository: JpaRepository<Worker, Long> {
    fun findWorkerByOwnerId(ownerId: Long,pageable: Pageable): Page<Worker>
    @Query(value = "SELECT * FROM workers w WHERE w.deleted = true and w.ownerid = :ownerId",
        countQuery = "SELECT count(*) FROM workers w WHERE w.deleted = true AND w.ownerid = :ownerId",
        nativeQuery = true)
    fun getDeletedWorkersByOwnerId(pageable: Pageable,ownerId: Long): Page<Worker>
}