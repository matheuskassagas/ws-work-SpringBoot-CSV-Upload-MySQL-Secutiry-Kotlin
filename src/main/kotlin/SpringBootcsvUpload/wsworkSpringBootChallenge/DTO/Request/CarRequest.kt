package SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Request

import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Car
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Factorie

class CarRequest (
        var factorieId: Int,
        var marcaNome: String,
        var model: String,
        var year: String,
        var fuel: String,
        var doors: String,
        var cost: String,
        var color: String
        ){

    fun toModel(carRequest: CarRequest, factorie: Factorie): Car {
        return Car(carRequest.model,
                carRequest.year,
                carRequest.fuel,
                carRequest.doors,
                carRequest.cost,
                carRequest.color,
                factorie)
    }

    fun toUpdate(carRequest: CarRequest, toUpdateCar: Car, factorieFinded: Factorie): Car {
        toUpdateCar.factorie=factorieFinded
        toUpdateCar.model=carRequest.model
        toUpdateCar.year=carRequest.year
        toUpdateCar.fuel=carRequest.fuel
        toUpdateCar.doors=carRequest.fuel
        toUpdateCar.cost=carRequest.cost
        toUpdateCar.color=carRequest.color
        return toUpdateCar
    }

}