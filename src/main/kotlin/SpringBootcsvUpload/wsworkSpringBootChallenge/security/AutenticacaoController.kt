package SpringBootcsvUpload.wsworkSpringBootChallenge.security

import SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Request.LoginForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AutenticacaoController {

    @Autowired
    lateinit var authManager: AuthenticationManager

    @Autowired
    lateinit var tokenManager: TokenManager

    @PostMapping
    fun autenticacao(@RequestBody form: @Valid LoginForm): ResponseEntity<*>? {
        val dadosLogin: UsernamePasswordAuthenticationToken = form.converte()
        return try {
            val authentication: Authentication = authManager.authenticate(dadosLogin)
            val token: String = tokenManager.gerarToken(authentication)
            ResponseEntity.ok(token)
        } catch (e: AuthenticationException) {
            ResponseEntity.badRequest().build<Any>()
        }
    }
}