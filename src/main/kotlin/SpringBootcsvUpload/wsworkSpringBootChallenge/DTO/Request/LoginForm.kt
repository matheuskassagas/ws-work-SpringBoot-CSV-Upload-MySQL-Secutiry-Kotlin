package SpringBootcsvUpload.wsworkSpringBootChallenge.DTO.Request

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken

class LoginForm(
        var email: String,
        var senha: String,
) {

    fun converte(): UsernamePasswordAuthenticationToken {
        return UsernamePasswordAuthenticationToken(email, senha)
    }
}