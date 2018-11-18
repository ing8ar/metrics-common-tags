package io.github.ing8ar.metrics.common.tags.spring.autoconfigure

import io.github.ing8ar.metrics.common.tags.core.CommonTagsManager
import io.github.ing8ar.metrics.common.tags.core.extractor.DefaultAppNameTagExtractor
import io.github.ing8ar.metrics.common.tags.core.extractor.DefaultHostNameTagExtractor
import io.github.ing8ar.metrics.common.tags.core.extractor.TagExtractor
import io.micrometer.core.instrument.Clock
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * Auto-configuration for set metrics common tags
 * @property metricsCommonTagsProperties - properties with common tag names
 * @property tagExtractors - set of [tag extractor][TagExtractor] beans
 */
@Configuration
@ConditionalOnBean(Clock::class)
@ConditionalOnProperty(prefix = "metrics.common", name = ["tags"], matchIfMissing = true)
@EnableConfigurationProperties(MetricsCommonTagsProperties::class)
open class MetricsCommonTagsAutoconfigure(
    private val metricsCommonTagsProperties: MetricsCommonTagsProperties,
    private val tagExtractors: Set<TagExtractor>
) {
    @Bean
    @ConditionalOnMissingBean
    open fun commonTagsManager() =
        CommonTagsManager(
            tags = metricsCommonTagsProperties.tags,
            extractors = tagExtractors
        )

    @Bean
    open fun commonTags(): MeterRegistryCustomizer<MeterRegistry> =
        MeterRegistryCustomizer {
            it.config().commonTags(*commonTagsManager().extractTags().toTypedArray())
        }

    /**
     * Initializing default extractor beans
     */
    @Configuration
    open class DefaultTagExtractorConfiguration {
        @Bean
        @ConditionalOnMissingBean
        open fun hostNameTagExtractor() = DefaultHostNameTagExtractor()

        @Bean
        @ConditionalOnMissingBean
        @ConditionalOnProperty(name = ["spring.application.name"], matchIfMissing = false)
        open fun appNameTagExtractor(@Value("\${spring.application.name}") appName: String) =
            DefaultAppNameTagExtractor(appName)
    }
}