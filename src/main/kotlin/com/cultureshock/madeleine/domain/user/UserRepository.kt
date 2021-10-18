package com.cultureshock.madeleine.domain.user

import com.cultureshock.madeleine.domain.user.enum.SocialType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User,Long> {

    fun findByEmailAndActive(email: String, active: Boolean = true): User?

    fun findByEmailAndSocialTypeAndActive(email: String, socialType: SocialType, active: Boolean = true): User?

    fun findByProviderIdAndSocialTypeAndActive(providerId: String, socialType: SocialType, active: Boolean = true): User?

    fun findByUsernameAndActive(username: String, active: Boolean = true): User?

    fun findByEmail(email: String): User?

    fun findByUsername(username: String): MutableIterable<List<User>>?
}