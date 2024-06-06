package com.flipkart.FLIPMED

import com.flipkart.FLIPMED.services.ClinicService
import com.flipkart.FLIPMED.services.PatientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication


class PixxelApplication



fun main(args: Array<String>) {
    val clinicService = ClinicService()
    val patientService = PatientService()
    clinicService.registerDoctor("Curious", "Cardiologist")
    clinicService.markDoctorAvailability("Curious", "9-10")
    clinicService.markDoctorAvailability("Curious", "9-10:30")
    clinicService.markDoctorAvailability("Curious", "9-10, 12-13, 16-17")
    patientService.searchAvailableSlotsBasedOnSpeciality("Cardiologist")
}
