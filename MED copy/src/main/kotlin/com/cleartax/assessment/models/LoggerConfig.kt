package com.cleartax.assessment.models

data class LoggerConfig(
        val tsFormat: String,
        val logLevel: LogLevel,
        val loggerType: LoggerType,
        val bufferSize: Int,
        val sinkType: SinkType
)