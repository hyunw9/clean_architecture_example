package com.arch.account.domain

import java.time.LocalDateTime

data class Account(
    val id: AccountId?,
    val baselineBalance: Money,
    val activityWindow: ActivityWindow
) {

    companion object {
        fun withOutId(
            val baselineBalance: Money,
            val activityWindow: ActivityWindow
        ): Account {
            return Account(
                null,
                baselineBalance,
                activityWindow
            )
        }

        fun withId(
            val id: AccountId,
            val baselineBalance: Money,
            val activityWindow: ActivityWindow
        ): Account {
            return Account(
                id,
                baselineBalance,
                activityWindow
            )
        }
    }

    fun calculateBalance(): Money {
        return Money.add(
            this.baselineBalance,
            this.activityWindow.calculateBalance(id)
        )
    }

    fun withDraw(
        money: Money,
        targetAccountId: AccountId
    ): Boolean {
        if (!mayWithdraw(money)) {
            return false
        }

        val withdrawal: Activity = Activity(
            this.id,
            this.id,
            targetAccountId,
            LocalDateTime.now(),
            money
        )
        this.activityWindow.addActivity(withdrawal)
        return true;
    }

    private fun mayWithdraw(money: Money): Boolean {
        return Money.add(
            this.calculateBalance(),
            money.negate()
        ).isPositive()
    }

    fun deposit(
        money: Money,
        sourceAccountId: AccountId
    ): Boolean {
        val deposit: Activity = Activity(
            this.id,
            sourceAccountId,
            this.id,
            LocalDateTime.now(),
            money
        )
        this.activityWindow.addActivity(deposit)
        return true
    }

    fun getActivities(): List<Activity> {
        return this.activityWindow.getActivities()
    }
}
