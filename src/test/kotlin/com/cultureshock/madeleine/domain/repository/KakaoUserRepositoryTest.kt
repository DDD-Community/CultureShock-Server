
package com.cultureshock.madeleine.domain.repository

import com.cultureshock.madeleine.domain.user.KakaoUser
import com.cultureshock.madeleine.domain.user.KakaoUserRepository
import com.cultureshock.madeleine.domain.user.User
import com.cultureshock.madeleine.domain.user.UserRepository
import com.cultureshock.madeleine.domain.user.enum.SocialType
import com.cultureshock.madeleine.rest.controller.SignInController
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import support.RepositoryTest

@RepositoryTest
class KakaoUserRepositoryTest @Autowired constructor(
    val userRepository: KakaoUserRepository
){
    companion object {
        private val log: Logger = LoggerFactory.getLogger(SignInController::class.java)
    }

    @BeforeEach
    @Throws(Exception::class)
    internal fun setUp() {
        log.debug("kakao userRepositorytest setUp >>>")

        var users = listOf(
            KakaoUser(
                socialType = SocialType.KAKAO
                , active = true
                , nickname = "아무개1"
                , username = "아무개1"
                , password = "abc123"
                , profileImage = "http://test.com"
                , providerId = "1"
                , birthday = "2000-01-01"
                , email = "a@kakao.com"
            ),
            KakaoUser(
                socialType = SocialType.KAKAO
                , active = true
                , nickname = "아무개2"
                , username = "아무개2"
                , password = "abc123"
                , profileImage = "http://test.com"
                , providerId = "2"
                , birthday = "2000-01-01"
                , email = "b@kakao.com"
            ),
            KakaoUser(
                socialType = SocialType.KAKAO
                , active = false
                , nickname = "아무개3"
                , username = "아무개2"
                , password = "abc123"
                , profileImage = "http://test.com"
                , providerId = "3"
                , birthday = "2000-01-01"
                , email = "c@kakao.com"
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
    fun `활동중인 유저들 중 이메일이 일치하는 사용자를 조회한다`() {
        assertThat(userRepository.findByEmailAndActive("b@kakao.com", true)!!.username).isEqualTo("아무개2")
    }

    @Test
    fun `활동안하는 중인 유저들 중 이메일,KAKAO 로그인이 일치하는 사용자를 조회한다`() {
        assertThat(userRepository.findByEmailAndSocialTypeAndActive("c@kakao.com", SocialType.KAKAO, false)!!.nickname).isEqualTo("아무개3")
    }

    @Test
    fun `활동중인 유저들 중 ProviderID,KAKAO 로그인이 일치하는 사용자를 조회한다`() {
        assertThat(userRepository.findByProviderIdAndSocialTypeAndActive("2", SocialType.KAKAO, true)!!.email).isEqualTo("b@kakao.com")
    }

    @ParameterizedTest
    @CsvSource("아무개2,2","아무개1,1")
    fun `활동중인 유저들 중 사용자이름이 일치하는 사용자를 모두 조회한다`(username: String, expectedSize: Int) {
        val result = userRepository.findByUsername(username)
        assertThat(result).hasSize(expectedSize)
    }

    @Test
    fun `이메일이 일치하는 사용자가 없을 때, null을 반환한다`() {
        assertThat( userRepository.findByEmail("notexist@email.com")).isNull()
    }
}




