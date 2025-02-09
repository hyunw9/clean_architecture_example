package com.arch.adapter.`in`.web

import com.arch.account.adapter.application.port.`in`.SendMoneyCommand
import com.arch.account.adapter.application.service.SendMoneyUseCase
import com.arch.account.domain.AccountId
import com.arch.account.domain.Money
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SendMoneyController(
    private val sendMoneyUseCase: SendMoneyUseCase
) {

    @PostMapping("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    fun sendMoney(
        @PathVariable sourceAccountId: String,
        @PathVariable targetAccountId: String,
        @PathVariable amount: Long
    ){
        val command : SendMoneyCommand = SendMoneyCommand(AccountId(sourceAccountId), AccountId(targetAccountId), Money(amount))
        sendMoneyUseCase.sendMoney(command)
    }
}
