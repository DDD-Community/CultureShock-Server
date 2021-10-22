package support

import com.cultureshock.madeleine.auth.security.JwtUser
import com.cultureshock.madeleine.auth.security.JwtUserFactory
import com.cultureshock.madeleine.domain.user.Authority
import com.cultureshock.madeleine.domain.user.KakaoUser
import com.cultureshock.madeleine.domain.user.User
import com.cultureshock.madeleine.domain.user.enum.AuthorityName
import com.cultureshock.madeleine.domain.user.enum.Sex
import com.cultureshock.madeleine.domain.user.enum.SocialType
import com.cultureshock.madeleine.rest.dto.request.SignInRequest
import org.springframework.security.core.userdetails.UserDetails

const val NICK_NAME: String = "아무개"
const val USER_NAME: String = "아무개"
const val EMAIL: String = "test@email.com"
const val PHONE: String = "010-0000-0000"

val SEX: Sex = Sex.MALE
val ACTIVE: Boolean = true
val PROFILE_IMAGE: String = "http://profile.com"
val THUMNAIL_IMAGE: String = "http://thumnail.com"
val PASSWORD: String = "test1234"
val BIRTH_DAY: String = "1994-01-01"
val SOCIAL_TYPE: SocialType = SocialType.KAKAO
val PROVIDER_ID: String = "123"

const val RANDOM_PASSWORD_TEXT: String = "nEw_p@ssw0rd"
const val VALID_TOKEN: String = "SOME_VALID_TOKEN"

fun createUser(
    id: Long = 1L,
    nickname: String = NICK_NAME
    , email: String = EMAIL
) : User{
    return User(
        id = id,
        nickname= nickname,
        email = email
    )
}

fun  createSignInRequest(
    nickname: String = NICK_NAME
    , email: String = EMAIL
): SignInRequest{
    return SignInRequest(
        nickname= nickname,
        email = email
    )
}

fun createKakaoUser(
    socialType: SocialType = SOCIAL_TYPE
    , active: Boolean = ACTIVE
    , nickname: String = NICK_NAME
    , username: String = USER_NAME
    , password: String = PASSWORD
    , profileImage: String = PROFILE_IMAGE
    , providerId: String = PROVIDER_ID
    , birthday: String = BIRTH_DAY
    , email: String = EMAIL
    , phone: String = PHONE
    , sex: Sex = SEX
) : KakaoUser {
    return KakaoUser(
        nickname= nickname
        ,email = email
        ,active = active
        ,socialType = socialType
        ,profileImage = profileImage
        ,providerId = providerId
        ,username = username
        ,password = password
        ,birthday = birthday
        ,phone = phone
        ,sex = sex)
}

fun createAuthority(
    authorityName: AuthorityName = AuthorityName.ROLE_LEVEL0
): Authority {
    return Authority(
        authorityName = authorityName
    )
}

fun createUserDetails(): UserDetails{
    return JwtUserFactory.create(createKakaoUser())
}