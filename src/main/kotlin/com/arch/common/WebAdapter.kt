package com.arch.common

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component

@Target(AnnotationTarget.TYPE)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class WebAdapter (
    @get:AliasFor(annotation = Component::class)
    val value: String = ""
){
}
