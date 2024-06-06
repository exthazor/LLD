package com.flipkart.FLIPMED.repositories

import com.flipkart.FLIPMED.models.Doctor
import com.flipkart.FLIPMED.models.Speciality
import org.springframework.stereotype.Repository
import java.lang.IllegalArgumentException

class DoctorRepository {

    private val doctors = mutableListOf<Doctor>()
    private val availability = mutableMapOf<Int, MutableSet<Int>>()
    private var nextId = 1

    fun addDoctor (doctor: Doctor) {

        if (doctors.any { it.name == doctor.name }) throw IllegalArgumentException("A doctor with same name exists.")
        val newDoctor = doctor.copy(id = nextId++)
        doctors.add(newDoctor)
        availability[newDoctor.id] = mutableSetOf()
    }

    fun findDoctorByName (doctorName: String): Doctor? {

        return doctors.find { it.name == doctorName }
    }

    fun setAvailability (doctorId: Int, slots: Set<Int>) {

        availability[doctorId]?.addAll(slots)
    }

    fun findDoctorsBySpeciality (speciality: Speciality): List<Doctor> {

        val filteredDoctors = doctors.filter { it.speciality == speciality }
        return filteredDoctors.sortedBy {
            availability[it.id]?.minOrNull()
        }
    }

    fun getAvailableSlots (doctorId: Int): MutableSet<Int>? {

        return availability[doctorId]
    }

    fun removeSlot(doctorId: Int, slot: Int) {

        availability[doctorId]?.remove(slot)
    }

}