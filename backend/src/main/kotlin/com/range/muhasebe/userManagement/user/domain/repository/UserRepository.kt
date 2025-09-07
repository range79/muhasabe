package com.range.muhasebe.userManagement.user.domain.repository

import com.range.muhasebe.userManagement.user.domain.model.User

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface  UserRepository : JpaRepository<User, Long>{

    fun findByUsername(username: String): User

    @Query(
        value = "SELECT * FROM users WHERE deleted = true ORDER BY id DESC",
        countQuery = "SELECT COUNT(*) FROM users WHERE deleted = true",
        nativeQuery = true
    )
    fun getDeletedUsers(pageable: Pageable): Page<User>

    @Query(
        value = "SELECT * FROM users WHERE userId = :userId AND deleted = true",
        nativeQuery = true
    )
    fun findDeletedUserById(userId: Long): Optional<User>
    fun existsByEmail(email: String): Boolean
    fun findByEmail(email: String): Optional<User>

}