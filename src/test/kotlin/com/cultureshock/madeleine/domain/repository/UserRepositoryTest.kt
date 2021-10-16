/*
package com.cultureshock.madeleine.domain.repository

import com.cultureshock.madeleine.domain.user.Authority
import com.cultureshock.madeleine.domain.user.User
import com.cultureshock.madeleine.domain.user.UserRepository
import com.cultureshock.madeleine.domain.user.enum.AuthorityName
import com.cultureshock.madeleine.domain.user.enum.SocialType
import com.cultureshock.madeleine.rest.controller.HealthControllerTest
import com.cultureshock.madeleine.rest.controller.SignInController
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager

@DataJpaTest
class UserRepositoryTest @Autowired constructor(
    val entityManager: TestEntityManager,
    val userRepository: UserRepository
){
    lateinit var user: User

    companion object {
        private val log: Logger = LoggerFactory.getLogger(SignInController::class.java)
    }

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        log.debug("test setup >>>")

        user = User(socialType = SocialType.KAKAO
            , active = true
            , nickname = "TEST_NICK"
            , username = "TEST_NAME"
            , password = "abc123"
            , profileImage = "http://test.com"
            , providerId = "123"
            , birthday = "2000-01-01"
            , email = "test123@kakao.com")

        entityManager.persist(user)
    }

    @AfterEach
    @Throws(Exception::class)
    fun setDown(){
        userRepository.deleteAll()
    }

    @Test
    fun `When_FindByEmailAndActive_Then_Return_User`() {
        val expected = userRepository.findByEmailAndActive("test123@kakao.com", true)
        assertThat(user).isEqualTo(expected)
    }

    @Test
    fun `When_FindByEmailAndSocialTypeAndActive_Then_Return_User`() {
        val expected = userRepository.findByEmailAndSocialTypeAndActive("test123@kakao.com", SocialType.KAKAO, true)
        assertThat(user).isEqualTo(expected)
    }

    @Test
    fun `When_FindByProviderIdAndSocialTypeAndActive_Then_Return_User`() {
        val expected = userRepository.findByProviderIdAndSocialTypeAndActive("123", SocialType.KAKAO, true)
        assertThat(user).isEqualTo(expected)
    }

    @Test
    fun `When_FindByUsernameAndActive_Then_Return_User`() {
        val expected = userRepository.findByUsernameAndActive("TEST_NAME", true)
        assertThat(user).isEqualTo(expected)
    }


}
*/
