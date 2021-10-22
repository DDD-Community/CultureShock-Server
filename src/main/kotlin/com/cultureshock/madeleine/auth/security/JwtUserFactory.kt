package com.cultureshock.madeleine.auth.security

import com.cultureshock.madeleine.domain.user.Authority
import com.cultureshock.madeleine.domain.user.KakaoUser
import com.cultureshock.madeleine.domain.user.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

class JwtUserFactory {

    companion object {
        fun create(user: KakaoUser): JwtUser {
            return JwtUser(
                uid = user.id,
                nickname = user.nickname,
                username = user.username,
                socialType = user.socialType,
                password = user.password,
                email = user.email,
                authorities = mapToGrantedAuthorities(user.authorities)
            )
        }

        private fun mapToGrantedAuthorities(authorities: List<Authority>): MutableList<GrantedAuthority> {
            return authorities.map { SimpleGrantedAuthority(it.authorityName.name) }.toMutableList()
        }
    }
}