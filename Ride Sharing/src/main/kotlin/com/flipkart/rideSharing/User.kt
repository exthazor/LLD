package com.flipkart.rideSharing

data class User (
        var name: String,
        var gender: Gender,
        var age: Int
)

enum class Gender { M, F }

