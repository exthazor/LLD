package com.flipkart.rideSharing

import kotlin.Exception

class UserService {

    private val users = mutableMapOf<String, User>()
    private val vehicles = mutableListOf<Vehicle>()
    private val offeredRides = mutableListOf<Ride>()
    private val ongoingRides = mutableListOf<Ride>()

    fun add_user(userName: String, gender: Gender, age: Int) {

        users.getOrPut(userName) { User(userName, gender, age) }
        println(users)
    }

    fun add_vehicle(userName: String, vehicleType: VehicleType, numberPlate: String) {

        try {
            val user = users[userName] ?: throw Exception("User Not Found!")
            if(vehicles.any { it.numberPlate == numberPlate }) throw Exception("Vehicle already exists!")
            vehicles.add(Vehicle(user, vehicleType, numberPlate))
        } catch (exception: Exception){
            println(exception.message)
        }
        println(vehicles)
    }

    fun offer_ride(userName: String, origin: String, seatsAvailable: Int,
                   vehicleType: VehicleType, numberPlate: String, destination: String) {

        try {
            val user = users[userName] ?: throw Exception("User Not Found!")
            val vehicle = vehicles.find {
                it.user.name == userName && it.vehicleType == vehicleType && it.numberPlate == numberPlate
            }?: throw Exception("User Not Found!")
            offeredRides.add(Ride(user, vehicle, destination, origin, seatsAvailable))
        } catch (exception: Exception){
            println(exception.message)
        }
    }

    fun select_ride(userName: String, source: String, destination: String, seats: Int,
                    selectionStrategy: Map<RideSelectionStrategy, Any>) {

        try {
            if (seats < 1 || seats > 2) throw Exception("Invalid number of seats!")
            val ridesMatchingCriteria = offeredRides.filter {
                it.origin == source && it.destination == destination && it.seatsAvailable >= seats }

            val ride = when {
                RideSelectionStrategy.MOST_VACANT in selectionStrategy -> {
                    ridesMatchingCriteria.maxByOrNull { it.seatsAvailable }
                }
                RideSelectionStrategy.PREFERRED_VEHICLE in selectionStrategy -> {
                    ridesMatchingCriteria.firstOrNull {
                        it.vehicle.vehicleType == selectionStrategy[RideSelectionStrategy.PREFERRED_VEHICLE] as? VehicleType }
                }
                else -> throw Exception("Improper selection strategy.")
            }

            if (ride == null) {
                println("No available ride matches the criteria for $userName")
            } else {
                println("${userName} has selected ride: ${ride.user.name}, origin: $source, destination: $destination, vehicle: ${ride.vehicle.vehicleType}, plate: ${ride.vehicle.numberPlate}")
                ongoingRides.add(ride)
            }
        } catch (exception: Exception) {
            println(exception.message)
        }
    }

}