package SpringBootcsvUpload.wsworkSpringBootChallenge.services

import SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Request.CarRequest
import SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Response.CarResponse
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.CarsRepositories
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Car
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Factorie
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.FactoriesRepositories
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

@Service
class CarService {

    @Autowired
    lateinit var carRepositories: CarsRepositories

    @Autowired
   lateinit var factorieRepositories: FactoriesRepositories

    @Transactional(readOnly = true)
    fun findCar(id: Int): CarResponse {
        val car: Car = carRepositories.findById(id)
        return CarResponse.Companion.toResponse(car)
    }

    @Transactional(readOnly = true)
    fun findAllCars(): List<CarResponse> {
        return carRepositories.findAllCars()
                .stream()
                .map { car -> CarResponse.toResponse(car) }.collect(Collectors.toList())
    }

    @Transactional
    fun createCar(carRequest: CarRequest): Car {
        val factorie: Factorie = factorieRepositories.findById(carRequest.factorieId)
        val car: Car = carRequest.toModel(carRequest, factorie)
        return carRepositories.createCar(car)
    }

    @Transactional
    fun updateCar(id: Int, carRequest: CarRequest): Car {
        val carFinded: Car = carRepositories.findById(id)
        val factorieFinded: Factorie = factorieRepositories.findById(carRequest.factorieId)
        require(!(carFinded == null || factorieFinded == null)) { "Id not null" }
        val carUpdate: Car = carRequest.toUpdate(carRequest, carFinded, factorieFinded)
        return carRepositories.updateCar(carUpdate)
    }

    @Transactional
    fun deleteCar(id: Int) {
        carRepositories.deleteCar(id)
    }


}
