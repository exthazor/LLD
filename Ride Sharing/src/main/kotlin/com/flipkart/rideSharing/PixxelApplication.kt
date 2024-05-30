package com.flipkart.rideSharing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PixxelApplication

fun main(args: Array<String>) {

    val userService = UserService()

    userService.add_user("Rohan", Gender.M, 36)
    userService.add_vehicle("Rohan", VehicleType.SWIFT, "KA-01-12345")

    userService.add_user("Shashank", Gender.M, 29)
    userService.add_vehicle("Shashank", VehicleType.BALENO, "TS-05-62395")

    userService.add_user("Nandini", Gender.F, 29)

    userService.add_user("Shipra", Gender.F, 27)
    userService.add_vehicle("Shipra", VehicleType.POLO, "KA-05-41491")
    userService.add_vehicle("Shipra", VehicleType.ACTIVA, "KA-12-12332")

    userService.add_user("Gaurav", Gender.M, 29)

    userService.add_user("Rahul", Gender.M, 35)
    userService.add_vehicle("Rahul", VehicleType.XUV, "KA-05-1234")

    println()

    //Offer 4 rides by 3 users

    userService.offer_ride("Rohan", "Hyderabad", 1, VehicleType.SWIFT, "KA-01-12345", "Bangalore")

    userService.offer_ride("Shipra", "Bangalore", 1, VehicleType.ACTIVA, "KA-12-12332", "Mysore")

    userService.offer_ride("Shipra", "Bangalore", 2, VehicleType.POLO, "KA-05-41491", "Mysore>")

    userService.offer_ride("Shashank", "Hyderabad", 2, VehicleType.BALENO, "TS-05-62395", "Bangalore")

    userService.offer_ride("Rahul", "Hyderabad", 5, VehicleType.XUV,  "KA-05-1234", "Bangalore")

    userService.offer_ride("Rohan", "Bangalore", 1, VehicleType.SWIFT, "KA-01-12345", "Pune")

    //Find rides for 4 users

    userService.select_ride("Nandini", "Bangalore", "Mysore", 1, mapOf(RideSelectionStrategy.MOST_VACANT to -1))

    //2(c) is the desired output.

    userService.select_ride("Gaurav", "Bangalore", "Mysore", 1, mapOf(RideSelectionStrategy.PREFERRED_VEHICLE to VehicleType.ACTIVA))

    //2(b) is the desired output

    userService.select_ride("Shashank", "Mumbai", "Bangalore", 1, mapOf(RideSelectionStrategy.MOST_VACANT to -1))

   // No rides found

    userService.select_ride("Rohan", "Hyderabad", "Bangalore", 1, mapOf(RideSelectionStrategy.PREFERRED_VEHICLE to VehicleType.BALENO))

    //2(d) is the desired output

    userService.select_ride("Shashank", "Hyderabad", "Bangalore", 1,mapOf(RideSelectionStrategy.PREFERRED_VEHICLE to VehicleType.POLO))
}
