package SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Response

import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Car

class CarResponse (
        var model: String,
        var year: String,
        var fuel: String,
        var doors: String,
        var cost: String,
        var color: String,
        var factorie: Int,
        var brandName: String
        ){

    companion object{
        fun toResponse(car: Car): CarResponse {
            return CarResponse(
                    car.model,
                    car.year,
                    car.fuel,
                    car.doors,
                    car.cost,
                    car.color,
                    car.factorie.id,
                    car.factorie.name)
        }
    }

}