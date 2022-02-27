package io.github.vinccool96.logging

expect class Logger(name: String?) {

    val name: String?

    fun info(message: String, e: Throwable? = null)

    fun warn(message: String, e: Throwable? = null)

    fun error(message: String, e: Throwable? = null)

    fun addHandler(handler: LogHandler)

    fun removeHandler(handler: LogHandler)

}