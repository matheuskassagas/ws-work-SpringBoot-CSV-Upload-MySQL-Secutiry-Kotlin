package SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.Hibernate
import javax.persistence.*

@Entity
data class Car(
        var model: String,
        var year: String,
        var fuel: String,
        var doors: String,
        var cost: String,
        var color: String,

        @field:ManyToOne(cascade = [(CascadeType.ALL)]) @field:JsonIgnore var factorie: Factorie) {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Car

        return id != null && id == other.id
    }

    override fun hashCode(): Int = 2133657150

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , model = $model , year = $year , fuel = $fuel , doors = $doors , cost = $cost , color = $color , factorie = $factorie )"
    }

}