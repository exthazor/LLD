package com.cleartax.assessment.services

import com.cleartax.assessment.interfaces.Sink
import com.cleartax.assessment.models.Message

class ConsoleSink: Sink {
    override fun write(message: Message, tsFormat: String) {
        println(message.format(tsFormat))
    }
}
