package io.github.ing8ar.metrics.common.tags.spring.example.config

import io.github.ing8ar.metrics.common.tags.core.extractor.DefaultHostNameTagExtractor
import io.github.ing8ar.metrics.common.tags.core.extractor.TagExtractor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class MyConfiguration {
    @Bean
    open fun customTagExtractor() = object : TagExtractor {
        override val tagName: String
            get() = "custom-tag"

        override fun extract() = "custom tag data"

    }

    @Bean
    open fun extendDefaultHostNameTagExtractor() = object : DefaultHostNameTagExtractor() {
        override fun extract() = "another host name"
    }
}