package SpringBootcsvUpload.wsworkSpringBootChallenge.repositories

import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Factorie
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FactorieRepositoryJPA : JpaRepository<Factorie, Int>{
}