package com.arch

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties

@ConfigurationProperties(prefix = "com.arch")
@EnableConfigurationProperties(PropertyConfiguration::class)
data class ConfigurationProperties(
    private val transferThreshold : Long = Long.MAX_VALUE
) {
}
