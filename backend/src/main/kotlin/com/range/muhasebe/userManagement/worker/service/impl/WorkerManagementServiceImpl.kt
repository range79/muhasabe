package com.range.muhasebe.userManagement.worker.service.impl

import com.range.muhasebe.common.exception.RoleMismatchException
import com.range.muhasebe.userManagement.user.domain.model.Role
import com.range.muhasebe.userManagement.user.domain.model.User
import com.range.muhasebe.userManagement.user.dto.RegisterDifferentRoleRequest
import com.range.muhasebe.userManagement.user.service.UserService
import com.range.muhasebe.userManagement.worker.dto.WorkerAddRequest
import com.range.muhasebe.userManagement.worker.dto.WorkerDetailedResponse
import com.range.muhasebe.userManagement.worker.dto.WorkerResponse
import com.range.muhasebe.userManagement.worker.service.WorkerManagementService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class WorkerManagementServiceImpl
    (


    private val userService: UserService

): WorkerManagementService {
    @Transactional
    override fun createWorker(workerAddRequest: WorkerAddRequest) {
        userService.registerDifferentRole(workerAddRequest.toRegisterDifferentRole())

    }


    @Transactional(readOnly = true)
    override fun listAllWorkers(pageable: Pageable): Page<WorkerResponse> {
        return   userService.getUserByRole(pageable, Role.ROLE_WORKER).map {
            it.toWorkerResponse()
        }

    }



    @Transactional
    override fun deleteWorker(workerId: UUID) {
        val user =userService.getDeletedUserById(workerId)
        if(user.role!=Role.ROLE_WORKER){
            throw RoleMismatchException("User is not worker $workerId")
        }
        user.delete()
        userService.updateUser(user)

    }
    @Transactional
    override fun restoreWorker(workerId: UUID) {

        val user =userService.getDeletedUserById(workerId)
        if(user.role!=Role.ROLE_WORKER){
            throw RoleMismatchException("User is not worker $workerId")
        }
        user.delete()
        userService.updateUser(user)
    }

    @Transactional(readOnly = true)
    override fun getDeletedWorkerDetailed(workerId: UUID): WorkerDetailedResponse {
        val worker = userService.getDeletedUserById(workerId)
        return worker.workerDetailedResponse()
    }
    @Transactional(readOnly = true)
    override fun getDeletedWorkers(pageable: Pageable): Page<WorkerResponse> {
        val users = userService.getDeletedUsersByRole(pageable, Role.ROLE_WORKER)
        return users.map {
            it.toWorkerResponse()
        }
    }


    fun User.workerDetailedResponse():WorkerDetailedResponse{
        return WorkerDetailedResponse(
            id =this.id,
            username = this.username,
            email = this.email,
            firstName = this.firstName,
            lastName = this.lastName,
            phoneNumber = this.phoneNUmber,
            startDate = this.startDate,
        )

    }
    private fun WorkerAddRequest.toRegisterDifferentRole(): RegisterDifferentRoleRequest {
        return RegisterDifferentRoleRequest(
            firstname = this.firstName,
            lastname = this.lastName,
            username = this.username,
            password = this.password,
            email = this.email,
            role = Role.ROLE_WORKER
        )
    }
    fun User.toWorkerResponse(): WorkerResponse{
        return WorkerResponse(
            id = this.id,
            username = this.username,
            email = this.email,
        )


    }


}