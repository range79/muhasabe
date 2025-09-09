package com.range.muhasebe.userManagement.worker.api

import com.range.muhasebe.userManagement.owner.dto.WorkerAddRequest
import com.range.muhasebe.userManagement.owner.dto.WorkerDetailedResponse
import com.range.muhasebe.userManagement.owner.dto.WorkerResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


import org.springframework.web.bind.annotation.RequestMapping

@RequestMapping("\${api.prefix}/workers/management")
interface WorkerManagerApi {


    @PostMapping
    fun createWorker(@RequestBody workerAddRequest: WorkerAddRequest)


    @DeleteMapping("/{workerId}")
    fun deleteWorker(@PathVariable workerId: Long)


    @GetMapping
    fun listAllWorkers(pageable: Pageable): Page<WorkerResponse>


    @PatchMapping("/{workerId}/restore")
    fun restoreWorker(@PathVariable workerId: Long)


    @GetMapping("/deleted/{workerId}")
    fun getDeletedWorkerDetailed(@PathVariable workerId: Long): WorkerDetailedResponse

    @GetMapping("/deleted")
    fun getDeletedWorkers(pageable: Pageable): Page<WorkerResponse>
}
