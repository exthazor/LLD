package com.cleartax.assessment.interfaces

import com.cleartax.assessment.models.Message

interface Sink {

    fun write (message: Message, tsFormat: String)
}