package com.range.muhasebe.userManagement.owner.service.impl

import com.range.muhasebe.common.util.SecurityContextUtil
import com.range.muhasebe.userManagement.owner.domain.repository.OwnerRepository
import com.range.muhasebe.userManagement.owner.dto.WorkerAddRequest
import com.range.muhasebe.userManagement.owner.dto.WorkerResponse
import com.range.muhasebe.userManagement.owner.service.OwnerService
import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.dto.RegisterDifferentRoleRequest
import com.range.muhasebe.userManagement.user.service.AuthService
import com.range.muhasebe.userManagement.user.service.UserService
import com.range.muhasebe.userManagement.worker.service.WorkerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.access.AccessDeniedException

class OwnerServiceImpl
    (
    private val authService: AuthService,
    private val ownerRepository: OwnerRepository,
    private val workerService: WorkerService,
    private val userService: UserService,
    private val securityContextUtil: SecurityContextUtil
): OwnerService {
    override fun createWorker(workerAddRequest: WorkerAddRequest) {
        val ownerId = securityContextUtil.getCurrentUserId()
        if (!userService.isOwner(ownerId)) {
            throw AccessDeniedException("User is not owner of this owner")
        }
        authService.registerDifferentRole(workerAddRequest.toRegisterDifferentRole())
    }

    override fun deleteWorker(workerId: Long) {

    }

    override fun listAllWorkers(pageable: Pageable): Page<WorkerResponse> {
        val ownerId= securityContextUtil.getCurrentUserId()

        if (!userService.isOwner(ownerId)){
            throw AccessDeniedException("You're not owner")
        }
        return   workerService.getWorkersByOwnerId(pageable, ownerId)
            .map { it->
                WorkerResponse(it.user.username,
                    it.user.firstName,
                    it.user.lastName,
                    it.phoneNumber,
                    it.user.email,
                    it.startDate) }

    }

    override fun restoreWorker(workerAddRequest: WorkerAddRequest) {
        TODO("Not yet implemented")
    }
    fun WorkerAddRequest.toRegisterDifferentRole(): RegisterDifferentRoleRequest {
        return RegisterDifferentRoleRequest(
            firstname = this.firstName,
            lastname =this.lastName,
            username =this.email,
            password =this.password,
            email = this.email,
            role= Role.ROLE_WORKER
        )
    }
}