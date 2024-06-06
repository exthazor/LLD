package com.cleartax.assessment

import com.cleartax.assessment.models.*
import com.cleartax.assessment.services.ConsoleSink
import com.cleartax.assessment.services.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class PixxelApplication

fun main(args: Array<String>) {

    val consoleSink = ConsoleSink()
    concurrentSync(consoleSink)
    concurrentAsync(consoleSink)
    nonConcurrentSync(consoleSink)
    nonConcurrentAsync(consoleSink)
}

fun concurrentSync(consoleSink: ConsoleSink) {

    val config = LoggerConfig("dd-MM-yyyy-HH-mm-ss", LogLevel.INFO, LoggerType.SYNC, 25, SinkType.STDOUT)
    val logger = Logger(listOf(consoleSink), config)

    logger.log(Message("Info message", LogLevel.INFO))
    logger.log(Message("Warn message", LogLevel.WARN))
    logger.log(Message("Debug message", LogLevel.DEBUG))
    logger.log(Message("Error message", LogLevel.ERROR))
    println("Concurrent sync messages logged.")
}

fun concurrentAsync(consoleSink: ConsoleSink) {

    val config = LoggerConfig("dd-MM-yyyy-HH-mm-ss", LogLevel.INFO, LoggerType.ASYNC, 25, SinkType.STDOUT)
    val logger = Logger(listOf(consoleSink), config)

    runBlocking {
        launch(Dispatchers.Default) {
            repeat(5) { i->
                logger.log(Message("Async message ${i+1}", LogLevel.INFO))
            }
        }.join()
    }
    println("Concurrent async messages logged.")
}

fun nonConcurrentSync(consoleSink: ConsoleSink) {

    val config = LoggerConfig("dd-MM-yyyy-HH-mm-ss", LogLevel.INFO, LoggerType.SYNC, 25, SinkType.STDOUT)
    val logger = Logger(listOf(consoleSink), config)
    logger.log(Message("Sync message", LogLevel.INFO))
    println("Non-concurrent sync message logged.")
}

fun nonConcurrentAsync(consoleSink: ConsoleSink) {

    val config = LoggerConfig("dd-MM-yyyy-HH-mm-ss", LogLevel.INFO, LoggerType.SYNC, 25, SinkType.STDOUT)
    val logger = Logger(listOf(consoleSink), config)
    runBlocking {
        launch(Dispatchers.Default) {
            logger.log(Message("Async message", LogLevel.INFO))
        }.join()
    }
    println("Non-concurrent async message logged.")
}
