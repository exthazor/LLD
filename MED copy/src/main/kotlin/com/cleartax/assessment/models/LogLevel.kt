package com.cleartax.assessment.models

enum class LogLevel (val priority: Int) {
    DEBUG(1),
    INFO(2),
    WARN(3),
    ERROR(4),
    FATAL(5)
}