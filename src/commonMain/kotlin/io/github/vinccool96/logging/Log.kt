package io.github.vinccool96.logging

class Log(val level: LogLevel, val message: String, val e: Throwable? = null, val loggerName: String? = null)
