package io.github.vinccool96.logging

import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object LogFactory {

    private val loggers = HashMap<String, Logger>()

    fun logger(name: String): Logger {
        return loggers.getOrPut(name) { Logger(name) }
    }

}