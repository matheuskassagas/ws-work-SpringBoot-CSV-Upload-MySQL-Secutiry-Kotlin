package SpringBootcsvUpload.wsworkSpringBootChallenge.controllers

import SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Request.CarRequest
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Car
import SpringBootcsvUpload.wsworkSpringBootChallenge.services.CarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/cars"])
class CarController {

    @Autowired
    lateinit var carService: CarService

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    fun findCar(@PathVariable id: Int): ResponseEntity<*> {
        return try {
            ResponseEntity<Any>(carService.findCar(id), HttpStatus.FOUND)
        } catch (e: Exception) {
            ResponseEntity("Car not found in Date Base.", HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping
    fun findAllCars(): ResponseEntity<*> {
        return ResponseEntity.ok().body(carService.findAllCars())
    }

    @RequestMapping(value = ["/cadastro"], method = [RequestMethod.POST])
    fun createCar(@RequestBody carRequest: @Valid CarRequest): ResponseEntity<*> {
        val car: Car = carService.createCar(carRequest)
        val uri = UriComponentsBuilder.fromPath("/cars/{id}").buildAndExpand(car.id).toUri()
        return ResponseEntity.created(uri).build<Any>()
    }

    @RequestMapping(value = ["/update/{id}"], method = [RequestMethod.PUT])
    fun updateCar(@PathVariable id: Int, @RequestBody carRequest: @Valid CarRequest): ResponseEntity<*> {
        return ResponseEntity.ok().body(carService.updateCar(id, carRequest))
    }

    @RequestMapping(value = ["/remove/{id}"], method = [RequestMethod.DELETE])
    fun deleteCar(@PathVariable id: Int): ResponseEntity<*> {
        carService.deleteCar(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
}