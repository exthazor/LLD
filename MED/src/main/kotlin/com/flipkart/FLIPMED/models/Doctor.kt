package com.flipkart.FLIPMED.models

data class Doctor (
        val id: Int,
        val name: String,
        val speciality: Speciality,
        val ranking: Double? = null
)