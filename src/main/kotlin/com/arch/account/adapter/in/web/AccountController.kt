package com.arch.adapter.`in`.web

import com.arch.account.adapter.application.service.SendMoneyUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController (
    private val getAccountBalanceQuery: GetAccountBalanceQuery,
    private val listAccountsQuery : ListAccountsQuery,
    private val loadAccountQuery : LoadAccountQuery,

    private val sendMoneyUseCase: SendMoneyUseCase,
    private val createAccountuseCase: CreateMoneyUseCase
){

    @GetMapping("/accounts")
    fun listAccounts() : AccountResource = listAccountsQuery.listAccounts()

    @GetMapping("/accounts/{accountId}/balance")
    fun getAccountBalance(@PathVariable accountId: String) : Long = getAccountBalanceQuery.getAccountBalance(accountId)

    @PostMapping("/accounts")
    fun createAccount(@RequestBody account : AccountResource) : AccountResource = createAccountuseCase.createAccount()

    @PostMapping("/accounts/send/{sourceAccountId}/{targetAccountId}/{amount}")
    fun sendMoney(
        @PathVariable sourceAccountId: String,
        @PathVariable targetAccountId: String,
        @PathVariable amount: Long
    ) : Boolean = sendMoneyUseCase.sendMoney(SendMoneyCommand(sourceAccountId, targetAccountId, amount))
}
