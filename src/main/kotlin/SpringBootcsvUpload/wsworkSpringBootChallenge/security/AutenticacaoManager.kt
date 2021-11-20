package SpringBootcsvUpload.wsworkSpringBootChallenge.security

import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Usuario
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional

@Service
class AutenticacaoManager : UserDetailsService {

    @PersistenceContext
    lateinit var entityManager: EntityManager
    @Value("\${security.username-query}") lateinit var query: String


    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        val consulta = entityManager!!.createQuery(query)
                .setParameter("username", username)
        val resultadoConsulta = consulta.resultList
        Assert.isTrue(resultadoConsulta.size <= 1, "Mais de um usuário cadastrado com o mesmo username.")
        if (resultadoConsulta.isEmpty()) {
            throw UsernameNotFoundException("Não foi possível encontrar um usuário com email:$username")
        }
        val objeto = consulta.singleResult
        return objeto as Usuario
    }
}