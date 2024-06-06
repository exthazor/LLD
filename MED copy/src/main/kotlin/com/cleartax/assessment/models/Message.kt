package com.cleartax.assessment.models

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Message (
        val content: String,
        val logLevel: LogLevel,
        val timeStamp: LocalDateTime = LocalDateTime.now()
) {
    fun format(tsFormat: String): String {

        val formatter = DateTimeFormatter.ofPattern(tsFormat)
        return "${timeStamp.format(formatter)} [$logLevel] $content"
    }
}