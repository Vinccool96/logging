package io.github.vinccool96.logging

import kotlin.native.concurrent.ThreadLocal
import kotlin.test.Test
import kotlin.test.assertTrue

class LoggerTest {

    @Test
    fun testNoException() {
        var works = false
        log.addHandler { works = true }
        log.info("some info")
        assertTrue(works)
    }

    @ThreadLocal
    companion object {

        private val log = LogFactory.logger("io.github.vinccool96.logging")

    }

}