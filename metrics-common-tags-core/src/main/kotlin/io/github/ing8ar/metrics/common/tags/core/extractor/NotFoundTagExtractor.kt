package io.github.ing8ar.metrics.common.tags.core.extractor

/**
 * System extractor, runs when extractor for given tag name not found
 */
class NotFoundTagExtractor : TagExtractor {
    override val tagName: String
        get() = "tagExtractorNotFound"
    override fun extract() = "tag extractor not found"
}