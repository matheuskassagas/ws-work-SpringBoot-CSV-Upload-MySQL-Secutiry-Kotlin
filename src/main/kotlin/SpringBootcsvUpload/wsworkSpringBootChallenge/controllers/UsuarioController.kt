package SpringBootcsvUpload.wsworkSpringBootChallenge.controllers

import SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Request.UsuarioRequest
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Usuario
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/usuario")
class UsuarioController {

    @PersistenceContext
    lateinit var entityManager: EntityManager

    @PostMapping
    @Transactional
    fun cadastro(@RequestBody form: @Valid UsuarioRequest): ResponseEntity<*> {
        val usuario: Usuario = form.converte(form)
        entityManager!!.persist(usuario)
        return ResponseEntity.ok().build<Any>()
    }
}