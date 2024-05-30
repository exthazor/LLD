package com.flipkart.rideSharing

data class Ride (
        val user: User,
        val vehicle: Vehicle,
        val origin: String,
        val destination: String,
        val seatsAvailable: Int,
)

enum class RideSelectionStrategy { PREFERRED_VEHICLE, MOST_VACANT }