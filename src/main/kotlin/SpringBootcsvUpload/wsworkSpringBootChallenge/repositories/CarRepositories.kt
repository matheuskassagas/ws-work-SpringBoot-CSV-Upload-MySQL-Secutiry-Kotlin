package SpringBootcsvUpload.wsworkSpringBootChallenge.repositories

import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Car
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class CarsRepositories {

    @Autowired
    lateinit var carsRepositoriesJPA: CarRepositoryJPA

    fun findById(id: Int): Car {
        return carsRepositoriesJPA.findById(id).orElse(null)
    }

    fun findAllCars(): List<Car> {
        return carsRepositoriesJPA.findAll()
    }

    fun createCar(car: Car): Car {
        carsRepositoriesJPA.save(car)
        return car
    }

    fun updateCar(car: Car): Car {
        return carsRepositoriesJPA.save(car)
    }

    fun deleteCar(id: Int) {
        carsRepositoriesJPA.deleteById(id)
    }
}