package io.github.ing8ar.metrics.common.tags.core

import io.github.ing8ar.metrics.common.tags.core.extractor.DefaultAppNameTagExtractor
import io.github.ing8ar.metrics.common.tags.core.extractor.DefaultHostNameTagExtractor
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.SpyK
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.entry
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CommonTagsManagerTest {
    @SpyK
    var hostNameTagExtractor = DefaultHostNameTagExtractor()

    var commonTagsManager: CommonTagsManager? = null

    @BeforeEach
    fun init() {
        MockKAnnotations.init(this)
        every { hostNameTagExtractor.extract() } returns ("host")

        commonTagsManager = CommonTagsManager(
            tags = setOf("app", "host", "no-extractor"),
            extractors = setOf(hostNameTagExtractor, DefaultAppNameTagExtractor("test"))
        )
    }

    @Test
    fun `CommonTagsManager extractTags returns correct data`() {
        assertThat(commonTagsManager?.extractTags())
            .hasSize(6)
            .containsExactly("app", "test", "host", "host", "no-extractor", "tag extractor not found")
    }

    @Test
    fun `CommonTagsManager extractTagsToMap returns correct data`() {
        assertThat(commonTagsManager?.extractTagsToMap())
            .contains(
                entry("app", "test"),
                entry("host", "host"),
                entry("no-extractor", "tag extractor not found")
            )
    }
}