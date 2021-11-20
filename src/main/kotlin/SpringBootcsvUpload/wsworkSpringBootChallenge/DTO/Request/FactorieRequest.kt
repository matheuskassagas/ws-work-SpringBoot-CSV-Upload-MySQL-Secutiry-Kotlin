package SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Request

import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Factorie

class FactorieRequest (
        val name: String,
        val countryCode: Int
){


    fun toModel(factorieRequest: FactorieRequest): Factorie {
        return Factorie(factorieRequest.name,
                factorieRequest.countryCode)
    }

    fun toUpdate(factorieRequest: FactorieRequest, factorieFound: Factorie): Factorie {
        factorieFound.name=factorieRequest.name
        factorieFound.countryCode=factorieRequest.countryCode
        return factorieFound
    }
}