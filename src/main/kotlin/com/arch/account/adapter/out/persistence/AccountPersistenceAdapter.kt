package com.arch.account.adapter.out.persistence

import com.arch.account.application.port.out.LoadAccountPort
import com.arch.account.application.port.out.UpdateAccountStatePort
import com.arch.account.domain.Account
import com.arch.account.domain.AccountId
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component
import java.time.LocalDate
import javax.security.auth.login.AccountNotFoundException

@Component
class AccountPersistenceAdapter(

    private val accountRepository: AccountRepository,
    private val activityRepository: ActivityRepository,
    private val accountMapper: AccountMapper
) : LoadAccountPort, UpdateAccountStatePort {

    fun loadAccount(
        accountId: AccountId,
        baselineDate: LocalDate
    ): Account {
        val account: AccountJpaEntity = accountRepository.findById(accountId.getId())
            .orElseThrow { EntityNotFoundException() }

        val activities: List<ActivityJpaEntity> =
            activityRepository.findByOwnerSince(accountId.getId(), baselineDate.atStartOfDay())

        val withdrawalBalance: Long = orZero(
            activityRepository
                .getWithdrawalBalanceUntil(accountId.getId(), baselineDate.atStartOfDay())
        )

        val depositBalance: Long = orZero(
            activityRepository
                .getDepositBalanceUtil(accountId.getId(), baselineDate.atStartOfDay())
        )

        return accountMapper.mapToDomainEntity(
            account,
            activities,
            withdrawalBalance,
            depositBalance
        )
    }

    fun updateAccountState(account: Account) {
        for (activity in account.activityWindow.getActivities()) {
            if (activity.id == null) {
                activityRepository.save(accountMapper.mapToJpaEntity(activity))
                
            }
        }
    }

    private fun orZero(value: Long?): Long = when (value) {
        null -> 0
        else -> value
    }
}
