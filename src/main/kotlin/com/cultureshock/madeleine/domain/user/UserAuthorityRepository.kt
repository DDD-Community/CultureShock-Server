package com.cultureshock.madeleine.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserAuthorityRepository: JpaRepository<UserAuthority, Long> {

    fun findByUid(uid: Long): UserAuthority?
}