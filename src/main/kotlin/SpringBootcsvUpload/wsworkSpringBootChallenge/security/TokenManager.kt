package SpringBootcsvUpload.wsworkSpringBootChallenge.security

import SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities.Usuario
import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class TokenManager {

    @Value("\${wswork.jwt.secret}")
    lateinit var secret: String

    //Gera o Token.
    fun gerarToken(authentication: Authentication): String {
        val usuario: Usuario = authentication.getPrincipal() as Usuario
        return Jwts.builder()
                .setIssuer("API WS Work")
                .setSubject(usuario.getUsername())
                .setIssuedAt(Date())
                .setExpiration(
                        Date.from(LocalDateTime.now()
                                .plusHours(1)
                                .atZone(ZoneId.of("America/Sao_Paulo")).toInstant())
                ).signWith(SignatureAlgorithm.HS256, secret).compact()
    }

    // Valida a assinatura do Token. Verifica se o Token é valido ou não.
    fun validaToken(token: String): Boolean {
        return try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
            true
        } catch (e: JwtException) {
            false
        }
    }

    //Pega o login do usuário.
    fun getUserName(token: String): String? {
        return try {
            val claims: Claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody()
            claims.getSubject()
        } catch (ex: SignatureException) {
            null
        }
    }
}