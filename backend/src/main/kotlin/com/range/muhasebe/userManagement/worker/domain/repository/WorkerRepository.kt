package com.range.muhasebe.userManagement.worker.domain.repository

import com.range.muhasebe.userManagement.worker.domain.model.Worker
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.Optional


interface WorkerRepository: JpaRepository<Worker, Long> {
    fun findWorkerByOwnerId(ownerId: Long,pageable: Pageable): Page<Worker>
    @Query(value = "SELECT * FROM worker_profiles w WHERE w.deleted = true and w.ownerid = :ownerId",
        countQuery = "SELECT count(*) FROM workers w WHERE w.deleted = true AND w.ownerid = :ownerId",
        nativeQuery = true)
    fun getDeletedWorkersByOwnerId(pageable: Pageable,ownerId: Long): Page<Worker>
    @Query("SELECT * FROM worker_profiles w where w.deleted = true and w.id = :workerId",
        nativeQuery = true
    )
    fun findDeletedWorker(workerId: Long): Optional<Worker>
    fun findByUserId(userId: Long): Worker?
}