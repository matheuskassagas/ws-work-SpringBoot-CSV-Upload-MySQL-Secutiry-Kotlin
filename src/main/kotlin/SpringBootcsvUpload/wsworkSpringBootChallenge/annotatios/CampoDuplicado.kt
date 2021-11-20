package SpringBootcsvUpload.wsworkSpringBootChallenge.annotatios

import java.lang.annotation.ElementType
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import java.lang.annotation.Target
import javax.validation.Constraint
import kotlin.reflect.KClass

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = [CampoDuplicadoValidator::class])
annotation class CampoDuplicado (

        val domainClass: KClass<*>,
        val fieldName: String,
        ) {
}