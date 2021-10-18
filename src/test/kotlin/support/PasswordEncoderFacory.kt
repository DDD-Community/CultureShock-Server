package support

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

fun createPasswordEncoder(): PasswordEncoder{
    return BCryptPasswordEncoder()
}