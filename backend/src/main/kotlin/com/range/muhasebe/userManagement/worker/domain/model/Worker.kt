package com.range.muhasebe.userManagement.worker.domain.model

import com.range.muhasebe.userManagement.user.domain.model.User
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "worker_profiles")
data class Worker (
    @Id
    @MapsId("userId")
    var id: Long,
    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    var user: User,
    var phoneNumber: String? = null,
    var ownerId: Long? = null,
    var startDate: LocalDateTime? = null,
    var deleted:Boolean = false,
    @Enumerated(EnumType.STRING)
    var permissions: WorkerPermissions
    )
{
    fun delete(){
        deleted=true
    }
    fun restore(){
        deleted=false
    }
}