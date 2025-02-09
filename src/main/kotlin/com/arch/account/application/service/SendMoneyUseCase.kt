package com.arch.account.application.service

interface SendMoneyUseCase {

    fun sendMoney(command: SendMoneyCommand): Boolean
}
