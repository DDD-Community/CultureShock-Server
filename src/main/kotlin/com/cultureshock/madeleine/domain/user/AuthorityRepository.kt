package com.cultureshock.madeleine.domain.user

import com.cultureshock.madeleine.domain.user.enum.AuthorityName
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AuthorityRepository: JpaRepository<Authority, Long> {

    fun findByAuthorityName(authorityName: AuthorityName): Authority
}