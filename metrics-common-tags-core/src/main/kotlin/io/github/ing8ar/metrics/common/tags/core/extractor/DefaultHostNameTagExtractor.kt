package io.github.ing8ar.metrics.common.tags.core.extractor

import mu.KLogging
import java.net.InetAddress

/**
 * Default host name extractor
 * If different behavior needs - extend this class
 */
open class DefaultHostNameTagExtractor : TagExtractor {
    companion object : KLogging()

    override val tagName: String
        get() = "host"

    override fun extract(): String = try {
        InetAddress.getLocalHost().hostName
    } catch (ex: Exception) {
        logger.warn {
            "Exception during extract localhost ${ex.message}," +
                    " return default value ${TagExtractor.UNDEFINED}"
        }
        TagExtractor.UNDEFINED
    }
}