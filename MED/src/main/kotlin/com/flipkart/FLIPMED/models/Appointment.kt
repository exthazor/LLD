package com.flipkart.FLIPMED.models

data class Appointment(
        val id: Int,
        val patientId: Int,
        val doctorId: Int,
        val slot: Int
)