package com.cultureshock.madeleine.domain.user

import com.cultureshock.madeleine.common.util.toSeoulEpochSecond
import com.cultureshock.madeleine.domain.AbstractBaseAuditEntity
import com.cultureshock.madeleine.domain.user.enum.Sex
import com.cultureshock.madeleine.domain.user.enum.SocialType
import java.time.LocalDateTime
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name="user")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    var nickname: String,

    val email: String,

    @Column(name = "lastlogin")
    var lastLogin: Long = LocalDateTime.now().toSeoulEpochSecond()!!

) : AbstractBaseAuditEntity()


@Entity
@Table(name="kakao_user")
class KakaoUser(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Column(name = "social_type")
    @Enumerated(value = EnumType.STRING)
    val socialType: SocialType,

    @Column(name = "provider_id")
    val providerId: String,

    val username: String,

    val password: String,

    var nickname: String,

    val email: String,

    var phone: String? = null,

    @Enumerated(value = EnumType.STRING)
    var sex: Sex? = null,

    var birthday: String? = null,

    @Column(name = "profile_image")
    var profileImage: String = "",

    @Column(name = "last_login")
    var lastLogin: ZonedDateTime = ZonedDateTime.now(),

    var active: Boolean = true,

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_authority",
        joinColumns = [JoinColumn(name = "uid", referencedColumnName = "id")],
        inverseJoinColumns = [JoinColumn(name = "authority_id", referencedColumnName = "id")]
    )
    var authorities: List<Authority> = mutableListOf()
) : AbstractBaseAuditEntity()