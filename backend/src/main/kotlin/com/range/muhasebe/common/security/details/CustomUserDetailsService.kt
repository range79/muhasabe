package com.range.muhasebe.common.security.details

import com.eloboostum.usermanagement.user.exception.UserNotFoundException
import com.range.muhasebe.userManagement.user.domain.repository.UserRepository
import com.range.muhasebe.userManagement.worker.domain.repository.WorkerRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val userRepository: UserRepository,
    private val workerRepository: WorkerRepository
)  {


    @Throws(UsernameNotFoundException::class)
    fun loadUserByUserID(userId: Long): UserDetails {

        val user =  userRepository.findById(userId).orElseThrow{
            UserNotFoundException("User with ID $userId not found")
        }
        val worker = workerRepository.findByUserId(user.id ?: -1)

        return CustomUserDetails(user,worker)
    }
    }
