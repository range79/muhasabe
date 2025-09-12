package com.range.muhasebe.userManagement.worker.service.impl

import com.range.muhasebe.common.util.SecurityContextUtil
import com.range.muhasebe.userManagement.user.dto.WorkerAddRequest
import com.range.muhasebe.userManagement.user.dto.WorkerDetailedResponse
import com.range.muhasebe.userManagement.user.dto.WorkerResponse
import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.dto.RegisterDifferentRoleRequest
import com.range.muhasebe.userManagement.user.service.AuthService
import com.range.muhasebe.userManagement.worker.domain.model.Worker
import com.range.muhasebe.userManagement.worker.domain.repository.WorkerRepository
import com.range.muhasebe.userManagement.worker.exception.WorkerNotFoundException
import com.range.muhasebe.userManagement.worker.service.WorkerManagementService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WorkerManagementServiceImpl
    (
    private val authService: AuthService,
    private val securityContextUtil: SecurityContextUtil,
    private val workerRepository: WorkerRepository
): WorkerManagementService {
    @Transactional
    override fun createWorker(workerAddRequest: WorkerAddRequest) {
        val ownerId = securityContextUtil.getCurrentUserId()
        val user = authService.registerDifferentRole(workerAddRequest.toRegisterDifferentRole())

        val worker = Worker(
            id = null,
            user = user,
            ownerId = ownerId,
            phoneNumber = workerAddRequest.phoneNumber,
            startDate = workerAddRequest.startDate,
            deleted = false,
            permissions = null,

            )
        workerRepository.save(worker)
    }


    @Transactional(readOnly = true)
    override fun listAllWorkers(pageable: Pageable): Page<WorkerResponse> {
        val ownerId= securityContextUtil.getCurrentUserId()
        return   workerRepository.findWorkerByOwnerId(ownerId= ownerId,pageable=pageable)
            .map {
                WorkerResponse(
                    it.user.id,
                    it.user.username,
                    it.user.email
                )
            }

    }

    @Transactional
    override fun deleteWorker(workerId: Long) {
        val worker= workerRepository.findById(workerId).orElseThrow { WorkerNotFoundException(null) }
        requireOwner(worker)
        worker.delete()
        workerRepository.save(worker)
    }
    override fun restoreWorker(workerId: Long) {

        val worker= workerRepository.findDeletedWorker(workerId).orElseThrow { WorkerNotFoundException(null) }
        requireOwner(worker)
        worker.restore()
        workerRepository.save(worker)
    }

    @Transactional(readOnly = true)
    override fun getDeletedWorkerDetailed(workerId: Long): WorkerDetailedResponse {

        val worker = workerRepository.findById(workerId).orElseThrow { WorkerNotFoundException("Worker with not found") }
        requireOwner(worker)

        return worker.workerDetailedResponse()


    }
    @Transactional(readOnly = true)
    override fun getDeletedWorkers(pageable: Pageable): Page<WorkerResponse> {
        val ownerId= securityContextUtil.getCurrentUserId()
        return workerRepository.getDeletedWorkersByOwnerId(pageable,ownerId).map {
            WorkerResponse(
                id = it.id,
                username = it.user.username,
                email = it.user.email,
            )
        }
    }

    //extension functions
    fun Worker.workerDetailedResponse():WorkerDetailedResponse{
        return WorkerDetailedResponse(
            id =this.id,
            username = this.user.username,
            email = this.user.email,
            firstName = this.user.firstName,
            lastName = this.user.lastName,
            phoneNumber = this.phoneNumber,
            startDate = this.startDate,
        )

    }
    private fun WorkerAddRequest.toRegisterDifferentRole(): RegisterDifferentRoleRequest {
        return RegisterDifferentRoleRequest(
            firstname = this.firstName,
            lastname = this.lastName,
            username = this.email,
            password = this.password,
            email = this.email,
            role = Role.ROLE_WORKER
        )
    }

    private fun requireOwner(worker: Worker) {
        if (worker.ownerId != securityContextUtil.getCurrentUserId()) {
            throw AccessDeniedException("Current user is not the owner of this worker")
        }
    }


}