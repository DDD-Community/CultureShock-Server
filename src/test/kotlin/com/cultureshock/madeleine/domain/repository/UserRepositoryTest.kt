package com.cultureshock.madeleine.domain.repository


import com.cultureshock.madeleine.domain.user.User
import com.cultureshock.madeleine.domain.user.UserRepository
import com.cultureshock.madeleine.rest.controller.SignInController
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import support.RepositoryTest

@RepositoryTest
class UserRepositoryTest @Autowired constructor(
    val userRepository: UserRepository
) {
    companion object {
        private val log: Logger = LoggerFactory.getLogger(SignInController::class.java)
    }

    @BeforeEach
    @Throws(Exception::class)
    internal fun setUp() {
        log.debug("userRepositorytest setUp >>>")

        var users = listOf(
            User(
                id= 1L, nickname = "아무개1", email = "a@kakao.com"
            ),
            User(
                id= 2L, nickname = "아무개2", email = "b@kakao.com"
            ),
            User(
                id= 3L, nickname = "아무개3", email = "c@kakao.com"
            )
        )
        userRepository.saveAll(users)
    }

    @AfterEach
    @Throws(Exception::class)
    fun setDown(){
        log.debug("userRepositorytest setDown >>>")
        userRepository.deleteAll()
    }

    @Test
    fun `유저들 중 PK ID가 일치하는 사용자를 조회한다`() {
        assertAll(
            { Assertions.assertThat(userRepository.findById(1L)!!.get().nickname).isEqualTo("아무개1") },
            { Assertions.assertThat(userRepository.findById(2L)!!.get().email).isEqualTo("b@kakao.com") }
        )
    }

    @Test
    fun `유저들 중 email 이 일치하는 사용자를 조회한다`() {
        assertAll(
            { Assertions.assertThat(userRepository.findByEmail("a@kakao.com")!!.nickname).isEqualTo("아무개1") },
            { Assertions.assertThat(userRepository.findByEmail("c@kakao.com")!!.nickname).isEqualTo("아무개3") }
        )
    }

}
