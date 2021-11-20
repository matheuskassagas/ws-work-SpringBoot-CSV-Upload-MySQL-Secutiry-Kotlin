package SpringBootcsvUpload.wsworkSpringBootChallenge.controllers

import SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Response.ResponseMessage
import SpringBootcsvUpload.wsworkSpringBootChallenge.csv.CSVHelper
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Car
import SpringBootcsvUpload.wsworkSpringBootChallenge.services.CSVService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@CrossOrigin("http://localhost:8080")
@RestController
@RequestMapping("/api/csv")
class CSVController {

    @Autowired
    lateinit var csvService: CSVService

    @PostMapping("/upload")
    fun uploadFile(@RequestParam("file") file: MultipartFile): ResponseEntity<ResponseMessage> {
        var message = ""
        if (CSVHelper.hasCSVFormat(file)) {
            return try {
                csvService.save(file)
                message = "Upload the file successfully: " + file.originalFilename
                val fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/csv/download/")
                        .path(file.originalFilename!!)
                        .toUriString()
                ResponseEntity.status(HttpStatus.OK).body<ResponseMessage>(ResponseMessage(message, fileDownloadUri))
            } catch (e: Exception) {
                message = "Could not upload the file: " + file.originalFilename + "!"
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(ResponseMessage(message, ""));
            }
        }
        message = "Please upload a cvs file!"
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body<ResponseMessage>(ResponseMessage(message, ""))
    }

    @GetMapping("/cars")
    fun getAllCarsUpload(): ResponseEntity<List<Car>> {
        return try {
            val carList: List<Car> = csvService.getAllCarUpload()
            if (carList.isEmpty()) {
                ResponseEntity<List<Car>>(HttpStatus.NO_CONTENT)
            } else ResponseEntity<List<Car>>(carList, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity<List<Car>>(null, HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

}