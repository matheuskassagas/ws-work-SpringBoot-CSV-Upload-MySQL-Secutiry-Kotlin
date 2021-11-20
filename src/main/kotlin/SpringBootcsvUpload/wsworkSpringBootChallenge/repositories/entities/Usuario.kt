package SpringBootcsvUpload.wsworkSpringBootChallenge.repositories.entities

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.*


@Entity
class Usuario (
        @field:NotBlank @field:Email val email: String ,
        @field:Size(min=6) @field:NotNull val senha: String,
        ): UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    @Column(updatable = false) val dataCriacao = LocalDateTime.now()



    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null
    }

    override fun getPassword(): String {
        return senha
    }

    override fun getUsername(): String {
        return email
    }

    override fun isAccountNonExpired(): Boolean {
       return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }


}