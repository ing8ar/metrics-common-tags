package io.github.ing8ar.metrics.common.tags.core.extractor

/**
 * Default app name extractor
 * If different behavior needs - extend this class
 */
open class DefaultAppNameTagExtractor(private val appName: String) : TagExtractor {
    override val tagName: String
        get() = "app"

    override fun extract() = appName
}