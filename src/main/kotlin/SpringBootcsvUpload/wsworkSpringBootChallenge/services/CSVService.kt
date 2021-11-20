package SpringBootcsvUpload.wsworkSpringBootChallenge.services

import SpringBootcsvUpload.wsworkSpringBootChallenge.csv.CSVHelper
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.CarRepositoryJPA
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Car
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Service
class CSVService {

    @Autowired
    lateinit var carRepositoryJPA : CarRepositoryJPA

    fun save(file: MultipartFile) {
        try {
            val carList: List<Car> = CSVHelper.csvToCar(file.inputStream)
            carRepositoryJPA.saveAllAndFlush(carList)
        } catch (e: IOException) {
            throw RuntimeException("fail to store csv data: " + e.message)
        }
    }

    fun getAllCarUpload(): List<Car> {
        return carRepositoryJPA.findAll()
    }
}