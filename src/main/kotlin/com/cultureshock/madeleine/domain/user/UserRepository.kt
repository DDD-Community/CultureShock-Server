package com.cultureshock.madeleine.domain.user

import com.cultureshock.madeleine.domain.user.enum.SocialType
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import java.util.*
import javax.annotation.Resource

/**
 * User
 */
@Repository
interface UserRepository : JpaRepository<User,Long>, CustomUserRepository{
}
interface CustomUserRepository{
    fun findByUserId(id: Long): User?
    fun findByEmail(email: String): User?
}

/**
 * KakaoUser
 */
@Repository
interface KakaoUserRepository : JpaRepository<KakaoUser,Long>, CustomKakaoUserRepository {
}
interface CustomKakaoUserRepository{
    fun findByEmailAndActive(email: String, active: Boolean = true): KakaoUser?
    fun findByEmailAndSocialTypeAndActive(email: String, socialType: SocialType, active: Boolean = true): KakaoUser?
    fun findByProviderIdAndSocialTypeAndActive(providerId: String, socialType: SocialType, active: Boolean = true): KakaoUser?
    fun findByUsernameAndActive(username: String, active: Boolean = true): KakaoUser?
    fun findByEmail(email: String): KakaoUser?
    fun findByUsername(username: String): MutableList<KakaoUser>??
}

/**
 * Query DSL
 */

class UserRepositorySupport(
    @Resource(name = "jpaQueryFactory")
    val query: JPAQueryFactory
) : QuerydslRepositorySupport(User::class.java), CustomUserRepository {

    override fun findByUserId(
        id: Long
    ): User? {
        return query.selectFrom(QUser.user)
            .where(QUser.user.id.eq(id))
            .fetchOne()
    }

    override fun findByEmail(
        email: String
    ): User? {
        return query.selectFrom(QUser.user)
            .where(QUser.user.email.eq(email))
            .fetchOne()
    }

}


class KakaoUserRepositorySupport(
    @Resource(name = "jpaQueryFactory")
    val query: JPAQueryFactory
): QuerydslRepositorySupport(KakaoUser::class.java), CustomKakaoUserRepository{

    override fun findByEmailAndActive(
        email: String,
        active: Boolean
    ): KakaoUser? {
        return query.selectFrom(QKakaoUser.kakaoUser)
            .where(QKakaoUser.kakaoUser.email.eq(email),
                QKakaoUser.kakaoUser.active.eq(active))
            .fetchOne()
    }

    override fun findByEmailAndSocialTypeAndActive(
        email: String,
        socialType: SocialType,
        active: Boolean
    ): KakaoUser? {
        return query.selectFrom(QKakaoUser.kakaoUser)
            .where(QKakaoUser.kakaoUser.email.eq(email), QKakaoUser.kakaoUser.active.eq(active))
        .fetchOne()
    }

    override fun findByProviderIdAndSocialTypeAndActive(
        providerId: String,
        socialType: SocialType,
        active: Boolean
    ): KakaoUser?{
        return query.selectFrom(QKakaoUser.kakaoUser)
            .where(QKakaoUser.kakaoUser.providerId.eq(providerId), QKakaoUser.kakaoUser.active.eq(active))
            .fetchOne()
    }

    override fun findByUsernameAndActive(
        username: String,
        active: Boolean
    ): KakaoUser?{
        return query.selectFrom(QKakaoUser.kakaoUser)
            .where(QKakaoUser.kakaoUser.username.eq(username), QKakaoUser.kakaoUser.active.eq(active))
            .fetchOne()
    }

    override fun findByEmail(
        email: String
    ): KakaoUser?{
        return query.selectFrom(QKakaoUser.kakaoUser)
            .where(QKakaoUser.kakaoUser.email.eq(email))
            .fetchOne()
    }

    override fun findByUsername(
        username: String
    ): MutableList<KakaoUser>? {
        return query.selectFrom(QKakaoUser.kakaoUser)
            .where(QKakaoUser.kakaoUser.username.eq(username))
            .fetch()
    }
}