package io.github.vinccool96.logging

fun interface LogHandler {

    fun publish(log: Log)

}