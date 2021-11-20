package SpringBootcsvUpload.wsworkSpringBootChallenge.security

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AutenticacaoTokenFilter (val tokenManager: TokenManager, val autenticacaoManager: AutenticacaoManager) : OncePerRequestFilter () {


    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, filterChain: FilterChain) {
        val token: String? = recuperaToken(httpServletRequest)
        if (token == null || token.isEmpty()) {
            filterChain.doFilter(httpServletRequest, httpServletResponse)
        } else {
            val valido: Boolean = tokenManager.validaToken(token)
            if (valido) {
                autenticaCliente(token)
            }
            filterChain.doFilter(httpServletRequest, httpServletResponse)
        }
    }

    open fun autenticaCliente(token: String) {
        val username: String? = tokenManager.getUserName(token)
        val usuario: UserDetails? = username?.let { autenticacaoManager!!.loadUserByUsername(it) }
        val authentication = UsernamePasswordAuthenticationToken(usuario, null, usuario?.getAuthorities())

        //Garante que o usuário está autenticado.
        SecurityContextHolder.getContext().setAuthentication(authentication)
    }

    open fun recuperaToken(httpServletRequest: HttpServletRequest): String? {
        val token = httpServletRequest.getHeader("Authorization")
        return if (Objects.nonNull(token) && !token.isEmpty()) {
            token
        } else null
    }
}