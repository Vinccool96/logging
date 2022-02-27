package io.github.vinccool96.logging

actual class Logger actual constructor(actual val name: String?) {

    private val handlers: ArrayList<LogHandler> = arrayListOf(LogHandler { log: Log ->
        val builder = StringBuilder()
        if (this.name != null) {
            builder.append("${this.name}: ")
        }
        builder.append("[${log.level.name}] ${log.message}")
        if (log.e != null) {
            builder.append(" ${log.e.stackTraceToString()}")
        }
        when (log.level) {
            LogLevel.INFO -> console.info(builder.toString())
            LogLevel.WARNING -> console.warn(builder.toString())
            LogLevel.ERROR -> console.error(builder.toString())
        }
    })

    actual fun info(message: String, e: Throwable?) {
        val log = Log(LogLevel.INFO, message, e, this.name)
        for (handler in this.handlers) {
            handler.publish(log)
        }
    }

    actual fun warn(message: String, e: Throwable?) {
        val log = Log(LogLevel.WARNING, message, e, this.name)
        for (handler in this.handlers) {
            handler.publish(log)
        }
    }

    actual fun error(message: String, e: Throwable?) {
        val log = Log(LogLevel.ERROR, message, e, this.name)
        for (handler in this.handlers) {
            handler.publish(log)
        }
    }

    actual fun addHandler(handler: LogHandler) {
        this.handlers.add(handler)
    }

    actual fun removeHandler(handler: LogHandler) {
        this.handlers.remove(handler)
    }

}