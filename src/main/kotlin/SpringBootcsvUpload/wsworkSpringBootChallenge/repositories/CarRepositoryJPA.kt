package SpringBootcsvUpload.wsworkSpringBootChallenge.repositories

import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepositoryJPA : JpaRepository<Car, Int>{
}