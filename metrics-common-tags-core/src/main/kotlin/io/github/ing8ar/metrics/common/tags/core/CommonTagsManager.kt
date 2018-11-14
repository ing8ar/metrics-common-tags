package io.github.ing8ar.metrics.common.tags.core

import io.github.ing8ar.metrics.common.tags.core.extractor.NotFoundTagExtractor
import io.github.ing8ar.metrics.common.tags.core.extractor.TagExtractor

/**
 * Manager helps to work with common tags
 * @property tags - set of tag names
 * @param extractors - set of [TagExtractor]
 */
class CommonTagsManager(
    private val tags: Set<String>,
    extractors: Set<TagExtractor>
) {
    private val extractorsMap = extractors.map { it.tagName to it }.toMap()

    private fun commonExtractTags() = tags.map {
        it to extractorsMap.getOrDefault(it, NotFoundTagExtractor()).extract()
    }

    /**
     * Extract tags data for [tags], running [TagExtractor.extract] for each tag name
     * @return List contains tag name and tag data in specific order -
     * tag0Name, tag0Data, tag1Name, tag1Data, etc.
     */
    fun extractTags() = commonExtractTags().flatMap { it.toList() }

    /**
     * Extract tags data for [tags], running [TagExtractor.extract] for each tag name
     * @return map of tag data, with tag name as a key
     */
    fun extractTagsToMap() = commonExtractTags().toMap()
}