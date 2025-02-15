package com.arch.account.application.service

import org.springframework.transaction.annotation.Transactional

@Transactional
class SendMoneyService(
    private val loadAccountPort: LoadAccountPort,
    private val updateAccountStatePort: UpdateAccountStatePort,
    private val accountLock : AccountLock
) : SendMoneyUseCase {

    override fun sendMoney(command: SendMoneyCommand): Boolean {
        TODO("Not yet implemented")
    }


}
