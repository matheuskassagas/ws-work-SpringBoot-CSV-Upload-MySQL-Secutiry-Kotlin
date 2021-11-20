package SpringBootcsvUpload.wsworkSpringBootChallenge.services

import SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Request.FactorieRequest
import SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Response.FactorieResponse
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Factorie
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.FactoriesRepositories
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
public class FactorieService {

    @Autowired
    lateinit var factoriesRepositories: FactoriesRepositories

    @Transactional(readOnly = true)
    fun findFactorie(id: Int): FactorieResponse {
        val factorie: Factorie = factoriesRepositories.findById(id)
        return FactorieResponse.toResponse(factorie)
    }

    @Transactional(readOnly = true)
    fun findAllFactories(): List<FactorieResponse> {
        return factoriesRepositories.findAllFactories()
    }

    @Transactional
    fun createFactorie(factorieRequest: FactorieRequest): Factorie {
        return factoriesRepositories.createFactorie(factorieRequest)
    }

    @Transactional
    fun updateFactorie(id: Int, factorieRequest: FactorieRequest): Factorie {
        val factorieFound: Factorie = factoriesRepositories.findById(id)
                ?: throw IllegalArgumentException("Id not null")
        val factorieUpdated: Factorie = factorieRequest.toUpdate(factorieRequest, factorieFound)
        return factoriesRepositories.updateFactorie(factorieUpdated)
    }

    @Transactional
    fun deleteFactorie(id: Int) {
        factoriesRepositories.deleteFactorie(id)
    }
}