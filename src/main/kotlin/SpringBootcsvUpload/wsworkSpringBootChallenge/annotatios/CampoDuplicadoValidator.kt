package SpringBootcsvUpload.wsworkSpringBootChallenge.annotatios

import org.springframework.util.Assert
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import kotlin.reflect.KClass

class CampoDuplicadoValidator : ConstraintValidator<CampoDuplicado, Object> {

    @PersistenceContext lateinit var entityManager: EntityManager
    var nomeCampo: String? = null
    var nomeClasse: KClass<*>? = null

    override fun initialize(constraintAnnotation: CampoDuplicado) {
        nomeCampo = constraintAnnotation.fieldName
        nomeClasse = constraintAnnotation.domainClass
    }

    override fun isValid(value: Object?, context: ConstraintValidatorContext?): Boolean {
        val query = entityManager!!.createQuery("select 1 from " + nomeClasse!!.java.name + " where " + nomeCampo + "=:value")
        query.setParameter("value", value)
        val resultadoConsulta = query.resultList
        Assert.state(resultadoConsulta.size <= 1, "O $nomeCampo digitado já foi cadastrado no sistema.")

        return resultadoConsulta.isEmpty()
    }


}