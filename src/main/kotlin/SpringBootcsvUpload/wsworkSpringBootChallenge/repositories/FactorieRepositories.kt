package SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities

import SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Request.FactorieRequest
import SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Response.FactorieResponse
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.FactorieRepositoryJPA
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.util.stream.Collectors

@Component
class FactoriesRepositories {

    @Autowired
    lateinit var factoriesRepositoriesJPA: FactorieRepositoryJPA

    fun findById(id: Int): Factorie {
        return factoriesRepositoriesJPA.findById(id).orElse(null)
    }

    fun findAllFactories(): List<FactorieResponse> {
        return factoriesRepositoriesJPA.findAll().stream().map { factorie -> FactorieResponse.toResponse(factorie) }.collect(Collectors.toList())
    }

    fun createFactorie(factorieRequest: FactorieRequest): Factorie {
        val factorie: Factorie = factorieRequest.toModel(factorieRequest)
        return factoriesRepositoriesJPA.save(factorie)
    }

    fun updateFactorie(factorieUpdated: Factorie): Factorie {
        return factoriesRepositoriesJPA.save(factorieUpdated)
    }

    fun deleteFactorie(id: Int) {
        factoriesRepositoriesJPA.deleteById(id)
    }
}