package com.flipkart.FLIPMED.utils

import java.lang.IllegalArgumentException

fun parseTimeSlots(slotText: String): Set<Int> {

    val slots = slotText.split(",")
    val parsedSlots = mutableSetOf<Int>()

    slots.forEach { slot ->
        val times = slot.trim().split("-")
        if (times.size != 2) {
            throw IllegalArgumentException("Sorry slots are 60 mins only")
        }

        val startHour = times[0].trim().toIntOrNull() ?: throw IllegalArgumentException("Sorry slots are 60 mins only.")
        val endHour = times[1].trim().toIntOrNull() ?: throw IllegalArgumentException("Sorry slots are 60 mins only.")

        if (endHour - startHour != 1) {
            throw IllegalArgumentException("Slots must be exactly one hour long.")
        }

        parsedSlots.add(startHour)
    }

    return parsedSlots
}