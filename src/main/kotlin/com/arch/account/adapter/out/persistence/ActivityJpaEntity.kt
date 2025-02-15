package com.arch.account.adapter.out.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "activity")
class ActivityJpaEntity (

    @Id
    @GeneratedValue
    private val id: Long,

    @Column
    private val timeStamp : LocalDateTime,

    @Column
    private val ownerAccountId : Long,

    @Column
    private val sourceAccountId: Long,

    @Column
    private val targetAccountId: Long,

    @Column
    private val amount : Long
){
}
