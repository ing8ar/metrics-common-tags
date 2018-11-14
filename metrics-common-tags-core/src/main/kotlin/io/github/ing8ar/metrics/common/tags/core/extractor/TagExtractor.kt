package io.github.ing8ar.metrics.common.tags.core.extractor

/**
 * Interface, provides base functionality to create specific tag extractor
 * @property tagName - tag name, which can de extracted by extractor
 */
interface TagExtractor {
    companion object {
        const val UNDEFINED = "undefined"
    }

    val tagName: String

    /**
     * Extract tag data
     * @return tag data, or [TagExtractor.UNDEFINED] if error happen
     */
    fun extract(): String
}