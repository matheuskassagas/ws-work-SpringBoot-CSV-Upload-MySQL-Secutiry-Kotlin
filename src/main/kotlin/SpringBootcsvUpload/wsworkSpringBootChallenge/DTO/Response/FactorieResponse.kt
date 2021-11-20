package SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Response

import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Car
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Factorie

class FactorieResponse (
        var id: Int,
        val name: String,
        val countryCode: Int?,
        val cars: List<Car>
        ){

    companion object {
        fun toResponse(factorie: Factorie): FactorieResponse {
            return FactorieResponse(factorie.id,
                    factorie.name,
                    factorie.countryCode,
                    factorie.cars)
        }
    }
}