package com.arch.account.application.port.`in`

import com.arch.account.domain.AccountId
import com.arch.account.domain.Money

class SendMoneyCommand(
    private val sourceIdAccount : AccountId,
    private val targetIdAccount : AccountId,
    private val money: Money

    // 입력 모델에서 사용자의 입력을 검증하는 로직을 추가
) {
}
