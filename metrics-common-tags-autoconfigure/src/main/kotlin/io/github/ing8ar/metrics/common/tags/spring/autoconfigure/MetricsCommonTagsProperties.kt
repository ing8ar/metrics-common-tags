package io.github.ing8ar.metrics.common.tags.spring.autoconfigure

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * {@link ConfigurationProperties} for configuring metrics common tags.
 * @property tags - set of common tag names
 */
@ConfigurationProperties(prefix = "metrics.common")
class MetricsCommonTagsProperties {
    lateinit var tags: Set<String>
}