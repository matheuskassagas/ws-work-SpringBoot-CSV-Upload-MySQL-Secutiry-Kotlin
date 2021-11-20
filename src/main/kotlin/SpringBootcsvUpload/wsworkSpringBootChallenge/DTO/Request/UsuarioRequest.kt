package SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Request

import SpringBootcsvUpload.wsworkSpringBootChallenge.annotatios.CampoDuplicado
import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Usuario
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

class UsuarioRequest(
        @field:NotBlank @field:Email @field:CampoDuplicado(Usuario::class, "email") var email: String,
        @field:NotBlank @field:Size(min = 6)
        var senha: String
) {

    fun converte(form: UsuarioRequest): Usuario {
        return Usuario(form.email, BCryptPasswordEncoder().encode(form.senha))
    }
}
