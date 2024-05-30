package com.flipkart.rideSharing

data class Vehicle (
        var user: User,
        var vehicleType: VehicleType,
        var numberPlate: String
)

enum class VehicleType { SWIFT, BALENO, POLO, XUV, ACTIVA }