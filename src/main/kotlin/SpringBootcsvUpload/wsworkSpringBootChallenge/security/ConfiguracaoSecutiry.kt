package SpringBootcsvUpload.wsworkSpringBootChallenge.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Configuration
@EnableWebSecurity
class ConfiguracaoSecurity : WebSecurityConfigurerAdapter () {

    @Autowired
    lateinit var autenticacaoManager: AutenticacaoManager
    @Autowired
    lateinit var tokenManager: TokenManager
    @PersistenceContext lateinit var entityManager: EntityManager

    @Bean
    @Throws(Exception::class)
    override fun authenticationManager(): AuthenticationManager? {
        return super.authenticationManager()
    }

    //Configura a parte de autenticação, login
    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(autenticacaoManager).passwordEncoder(BCryptPasswordEncoder())
    }

    //Configura a parte de autorização, URLS liberadas.
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                .antMatchers(HttpMethod.POST, "/usuario").permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .anyRequest().authenticated().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(AutenticacaoTokenFilter(tokenManager, autenticacaoManager), UsernamePasswordAuthenticationFilter::class.java)
    }

    //Configura recursos estáticos(js, css, imagens, etc.)
    @Throws(Exception::class)
    override fun configure(web: WebSecurity) {
        web.ignoring().antMatchers("/h2-console/**")
        web.ignoring().antMatchers("/actuator/**")
    }
}