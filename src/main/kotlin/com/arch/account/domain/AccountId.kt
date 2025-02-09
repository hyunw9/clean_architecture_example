package com.arch.account.domain


data class AccountId (

    private val id: Long
){

    fun getId(): Long{
        return this.id
    }
}
