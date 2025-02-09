package com.arch.account.domain

class ActivityWindow {

    private val start: Long
    private val end: Long

    constructor(start: Long, end: Long) {
        this.start = start
        this.end = end
    }

    fun getStart(): Long {
        return this.start
    }

    fun getEnd(): Long {
        return this.end
    }

    fun getActivities(): List<Activity> {
        return listOf()
    }


}
