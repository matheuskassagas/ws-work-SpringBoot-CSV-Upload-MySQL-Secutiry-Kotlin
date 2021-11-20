package SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities

import org.hibernate.Hibernate
import javax.persistence.*

@Entity
data class Factorie(
        var name: String,
        var countryCode: Int?
        ) {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @OneToMany(mappedBy = "factorie")
    lateinit var cars: List<Car>

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Factorie

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 256642969

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name , countryCode = $countryCode , cars = $cars )"
    }

}