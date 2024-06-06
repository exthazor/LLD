package com.flipkart.FLIPMED.services

import com.flipkart.FLIPMED.models.Doctor
import com.flipkart.FLIPMED.models.Speciality
import com.flipkart.FLIPMED.repositories.DoctorRepository
import com.flipkart.FLIPMED.utils.parseTimeSlots
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

class ClinicService {

    private var doctorRepository = DoctorRepository()

    fun registerDoctor(doctorName: String, doctorSpeciality: String) {

        try {
            val specialityType = Speciality.values().find { it.name.equals(doctorSpeciality, true) }
                    ?: throw IllegalArgumentException("Speciality not recognised")
            val doctor = Doctor (id = 0, name = doctorName, speciality = specialityType)
            doctorRepository.addDoctor(doctor)
            println("Welcome Dr. $doctorName !!")
        } catch (exception: Exception) {
            println(exception.message)
        }
    }

    fun markDoctorAvailability (doctorName: String, slotText: String) {

        try {
            val doctor = doctorRepository.findDoctorByName(doctorName) ?: throw Exception("Doctor not found")
            val slots = parseTimeSlots(slotText)
            doctorRepository.setAvailability(doctor.id, slots)
            println("Done Doc!")
        } catch (e: Exception) {
            println(e.message)
        }
    }

}