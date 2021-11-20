package SpringBootcsvUpload.wsworkSpringBootChallenge.controllers

import SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Request.FactorieRequest
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Factorie
import SpringBootcsvUpload.wsworkSpringBootChallenge.services.FactorieService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/factories"])
class FactorieController {

    @Autowired
    lateinit var factorieService: FactorieService


    @RequestMapping(value = ["/{id}"], method = [RequestMethod.GET])
    fun findFactorie(@PathVariable id: Int): ResponseEntity<*> {
        return try {
            ResponseEntity<Any>(factorieService.findFactorie(id), HttpStatus.FOUND)
        } catch (e: Exception) {
            ResponseEntity("Car not found in Date Base.", HttpStatus.NOT_FOUND)
        }
    }

    @RequestMapping(method = [RequestMethod.GET])
    fun findAllFactories(): ResponseEntity<*> {
        return ResponseEntity.ok().body(factorieService.findAllFactories())
    }

    @RequestMapping(value = ["/cadastro"], method = [RequestMethod.POST])
    fun createFactorie(@RequestBody factorieRequest: @Valid FactorieRequest): ResponseEntity<*> {
        val factorie: Factorie = factorieService.createFactorie(factorieRequest)
        val uri = UriComponentsBuilder.fromPath("/factories/{id}").buildAndExpand(factorie.id).toUri()
        return ResponseEntity.created(uri).build<Any>()
    }

    @RequestMapping(value = ["/update/{id}"], method = [RequestMethod.PUT])
    fun updateFactorie(@PathVariable id: Int, @RequestBody factorieRequest: @Valid FactorieRequest): ResponseEntity<*> {
        return ResponseEntity.ok().body(factorieService.updateFactorie(id, factorieRequest))
    }

    @RequestMapping(value = ["/remove/{id}"], method = [RequestMethod.DELETE])
    fun deleteFactorie(@PathVariable id: Int): ResponseEntity<*> {
        factorieService.deleteFactorie(id)
        return ResponseEntity<Any>(HttpStatus.OK)
    }
}