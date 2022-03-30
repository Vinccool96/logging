package io.github.vinccool96.logging

actual class Logger actual constructor(actual val name: String?) {

    private val baseHandler = LogHandler { log: Log ->
        val builder = StringBuilder()
        if (this.name != null) {
            builder.append("${this.name}: ")
        }
        builder.append("[${log.level.name}] ${log.message}")
        if (log.e != null) {
            builder.append(" ${log.e.stackTraceToString()}")
        }
        when (log.level) {
            LogLevel.INFO -> println(builder.toString())
            LogLevel.WARNING -> println(YELLOW + builder.toString() + RESET)
            LogLevel.ERROR -> println(RED + builder.toString() + RESET)
        }
    }

    private val handlers: ArrayList<LogHandler> = arrayListOf(this.baseHandler)

    actual var baseHandlerActivated: Boolean = true
        set(value) {
            if (field != value) {
                if (value) {
                    this.handlers.add(0, this.baseHandler)
                } else {
                    this.handlers.removeAt(0)
                }
            }
            field = value
        }

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

    companion object {

        private const val RESET = "\u001B[0m"

        private const val RED = "\u001B[0;31m"

        private const val YELLOW = "\u001B[33m"

    }

}