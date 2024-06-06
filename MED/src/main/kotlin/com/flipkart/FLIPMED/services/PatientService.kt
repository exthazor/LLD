package com.flipkart.FLIPMED.services

import com.flipkart.FLIPMED.models.Appointment
import com.flipkart.FLIPMED.models.Doctor
import com.flipkart.FLIPMED.models.Patient
import com.flipkart.FLIPMED.models.Speciality
import com.flipkart.FLIPMED.repositories.AppointmentRepository
import com.flipkart.FLIPMED.repositories.DoctorRepository
import com.flipkart.FLIPMED.repositories.PatientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

class PatientService {

    private var doctorRepository = DoctorRepository()

    private var patientRepository = PatientRepository()

    private var appointmentRepository = AppointmentRepository()

    fun registerPatient (patientName: String): String {

        return try {
            val patient = Patient (id = 0, name = patientName)
            patientRepository.addPatient(patient)
            "$patientName registered successfully"
        } catch (exception: Exception) {
            exception.message ?: "An error occurred"
        }
    }

    fun searchAvailableSlotsBasedOnSpeciality (speciality: String) {

        val specialityType = Speciality.values().find { it.name.equals(speciality, true) }
        if (specialityType == null) {
            println("Speciality not recognised")
            return
        }
        val doctors = doctorRepository.findDoctorsBySpeciality(specialityType)
        val answer = StringBuilder()

        doctors.forEach { doctor ->
            val slots = doctorRepository.getAvailableSlots(doctor.id)
            if (!slots.isNullOrEmpty()) {
                slots.sorted().forEach {
                    answer.append("Dr.${doctor.name}:, ${it}:00 - ${it+1}:00\n")
                }
            }
        }
        if (answer.isEmpty()) println("No available slots for speciality $speciality") else answer.toString()
    }

    fun bookAppointment (patientName: String, doctorName: String, slot: String): String {

        val doctor = doctorRepository.findDoctorByName(doctorName)
        val patient = patientRepository.findPatientByName(patientName)

        if (doctor == null) {
            return "Doctor not found."
        }
        if (patient == null) {
            return "Patient not found."
        }

        val realSlot = slot.split(":")[0].toIntOrNull() ?: return "Invalid slot format."

        val existingAppointment = appointmentRepository.findAppointmentsByPatientId(patient.id)
                .any { it.slot == realSlot }
        if (existingAppointment) {
            return "Patient already has an appointment at this slot."
        }

        val slots = doctorRepository.getAvailableSlots(doctor.id)
        return if (slots != null && realSlot in slots) {
            val appointment = Appointment(appointmentRepository.getNextAppointmentId(), patient.id, doctor.id, realSlot)
            appointmentRepository.addAppointment(appointment)
            doctorRepository.removeSlot(doctor.id, realSlot)
            "Appointment booked successfully at ${realSlot}:00."
        } else {
            "Slot not available."
        }
    }


}