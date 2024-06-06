package com.cleartax.assessment.services

import com.cleartax.assessment.interfaces.Sink
import com.cleartax.assessment.models.LoggerConfig
import com.cleartax.assessment.models.LoggerType
import com.cleartax.assessment.models.Message
import java.util.concurrent.BlockingQueue
import java.util.concurrent.Executors
import java.util.concurrent.LinkedBlockingQueue

class Logger(private val sinks: List<Sink>, private val config: LoggerConfig){

    private val messageQueue: BlockingQueue<Message> = LinkedBlockingQueue(config.bufferSize)
    private val executor = if (config.loggerType == LoggerType.ASYNC) {
        Executors.newSingleThreadScheduledExecutor()
    } else {
        null
    }
    fun log (message: Message) {

        if (message.logLevel.priority >= config.logLevel.priority) {
            if(config.loggerType == LoggerType.SYNC) { // sync part
                sinks.forEach { sink -> sink.write(message, config.tsFormat) }
            } else {
                executor?.execute { // async part
                    messageQueue.put(message)
                    val msg = messageQueue.take()
                    sinks.forEach { sink -> sink.write(msg, config.tsFormat) }
                }
            }
        }
    }
}