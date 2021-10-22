package com.cultureshock.madeleine.domain.user

import com.cultureshock.madeleine.domain.user.enum.SocialType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface KakaoUserRepository : JpaRepository<KakaoUser,Long> {

    fun findByEmailAndActive(email: String, active: Boolean = true): KakaoUser?

    fun findByEmailAndSocialTypeAndActive(email: String, socialType: SocialType, active: Boolean = true): KakaoUser?

    fun findByProviderIdAndSocialTypeAndActive(providerId: String, socialType: SocialType, active: Boolean = true): KakaoUser?

    fun findByUsernameAndActive(username: String, active: Boolean = true): KakaoUser?

    fun findByEmail(email: String): KakaoUser?

    fun findByUsername(username: String): MutableIterable<List<KakaoUser>>?
}

@Repository
interface UserRepository : JpaRepository<User,Long> {

    override fun findById(id: Long): Optional<User>
    fun findByEmail(email: String): User?

}