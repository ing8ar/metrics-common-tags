package io.github.ing8ar.metrics.common.tags.core.extractor

import io.mockk.every
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.net.InetAddress
import java.net.UnknownHostException

class DefaultHostNameTagExtractorTest {

    @Test
    fun `DefaultHostNameTagExtractor extract correct hostname`() {
        val defaultHostNameTagExtractor = DefaultHostNameTagExtractor()
        assertThat(defaultHostNameTagExtractor.extract())
            .isEqualTo(InetAddress.getLocalHost().hostName)
    }

    @Test
    fun `DefaultHostNameTagExtractor process exception while extract hostname`() {
        mockkStatic(InetAddress::class)
        every {
            InetAddress.getLocalHost()
        } throws UnknownHostException()

        val defaultHostNameTagExtractor = DefaultHostNameTagExtractor()
        assertThat(defaultHostNameTagExtractor.extract())
            .isEqualTo(TagExtractor.UNDEFINED)

    }

    @AfterEach
    fun runAfter() {
        unmockkStatic(InetAddress::class)
    }
}