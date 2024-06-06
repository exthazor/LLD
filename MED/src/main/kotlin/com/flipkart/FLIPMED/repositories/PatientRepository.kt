package com.flipkart.FLIPMED.repositories

import com.flipkart.FLIPMED.models.Appointment
import com.flipkart.FLIPMED.models.Doctor
import com.flipkart.FLIPMED.models.Patient
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

class PatientRepository {

    private val patients = mutableListOf<Patient>()
    private var nextId = 1

    fun addPatient (patient: Patient) {

        if (patients.any { it.name == patient.name }) throw IllegalArgumentException("A patient with same name exists.")
        val newPatient = patient.copy(id = nextId++)
        patients.add(newPatient)
    }

    fun findPatientByName (patientName: String): Patient? {

        return patients.find { it.name == patientName }
    }

}