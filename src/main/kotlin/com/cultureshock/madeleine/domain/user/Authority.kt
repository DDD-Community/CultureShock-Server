package com.cultureshock.madeleine.domain.user

import com.cultureshock.madeleine.domain.AbstractBaseAuditEntity
import com.cultureshock.madeleine.domain.user.enum.AuthorityName
import javax.persistence.*

@Entity
@Table(name = "authority")
class Authority(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = -1,

    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    val authorityName: AuthorityName,

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    var user: List<KakaoUser> = mutableListOf()
): AbstractBaseAuditEntity()