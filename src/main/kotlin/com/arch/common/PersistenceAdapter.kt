package com.arch.common

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component
import java.lang.annotation.ElementType

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Component
annotation class PersistenceAdapter(
    @get:AliasFor(annotation = Component::class, attribute = "value")
    val value: String = ""
)
