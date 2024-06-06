package com.flipkart.FLIPMED.repositories

import com.flipkart.FLIPMED.models.Appointment
import com.flipkart.FLIPMED.models.Doctor
import org.springframework.stereotype.Repository

class AppointmentRepository {

    private val appointments = mutableListOf<Appointment>()
    private var nextId = 1

    fun addAppointment (appointment: Appointment) {

        val newAppointment = appointment.copy(id = nextId++)
        appointments.add(newAppointment)
    }

    fun findAppointmentsByPatientId (patientId: Int): List<Appointment> {

        return appointments.filter { it.patientId == patientId }
    }

    fun getNextAppointmentId(): Int {

        return nextId
    }

}