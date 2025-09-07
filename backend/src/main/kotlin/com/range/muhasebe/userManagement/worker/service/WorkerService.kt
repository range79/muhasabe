package com.range.muhasebe.userManagement.worker.service

import com.range.muhasebe.userManagement.user.domain.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

interface WorkerService {
    fun getWorkersByOwnerId(pageable: Pageable): Page<User>
    fun editProfile()
}