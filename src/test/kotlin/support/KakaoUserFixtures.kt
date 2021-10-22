package support

import com.cultureshock.madeleine.auth.client.kakao.dto.response.KakaoUserAccount
import com.cultureshock.madeleine.auth.client.kakao.dto.response.KakaoUserProfile
import com.cultureshock.madeleine.auth.client.kakao.dto.response.KakaoUserProperties
import com.cultureshock.madeleine.auth.client.kakao.dto.response.KakaoUserResponse
import com.cultureshock.madeleine.rest.dto.response.UserResponse

fun createUserResponse(
    id:Long = 1L
    , nickname: String = NICK_NAME
    , email: String = EMAIL
): UserResponse {
    return UserResponse(
        id = id
        , nickname = nickname
        , email = email
    )
}

fun createKakaoUserResponse(
    id: Long = 1,
    connectedAt: String = "2020-01-01",
    properties: KakaoUserProperties = createKakoUserProperties(),
    kakaoAccount: KakaoUserAccount = createKakaoUserAccount()
): KakaoUserResponse{
    return KakaoUserResponse(
        id = id,
        connectedAt = connectedAt,
        properties = properties,
        kakaoAccount = kakaoAccount
    )
}

fun createKakoUserProperties(
    nickname: String = NICK_NAME,
    profileImage: String = PROFILE_IMAGE,
    thumbnailImage: String = THUMNAIL_IMAGE
): KakaoUserProperties {
    return KakaoUserProperties(
        nickname = nickname,
        profileImage = profileImage,
        thumbnailImage = thumbnailImage
    )
}
fun createKakaoUserAccount(
    profileNeedsAgreement: Boolean = true,
    profile: KakaoUserProfile = createKakaoUserProfile(),
    hasEmail: Boolean = true,
    isEmailValid: Boolean = true,
    email: String = EMAIL,
    hasAgeRange: Boolean = true,
    ageRange: String = "",
    ageRangeNeedsAgreement: Boolean = true,
    hasBirthday: Boolean = true,
    hasGender: Boolean = true,
    genderNeedsAgreement: Boolean = true,
    gender: String = "male",
    isEmailVerfied: Boolean = true,
    birthday: String = BIRTH_DAY,
    birthdayNeedsAgreement: Boolean = true,
    birthdayType: String = "SOLAR"
): KakaoUserAccount {
    return KakaoUserAccount(
        profileNeedsAgreement = profileNeedsAgreement,
        profile = profile,
        hasEmail = hasEmail,
        isEmailValid = isEmailValid,
        email = email,
        hasAgeRange = hasAgeRange,
        ageRange = ageRange,
        ageRangeNeedsAgreement = ageRangeNeedsAgreement,
        hasBirthday = hasBirthday,
        hasGender = hasGender,
        genderNeedsAgreement = genderNeedsAgreement,
        gender = gender,
        birthday = birthday,
        birthdayNeedsAgreement = birthdayNeedsAgreement,
        birthdayType = birthdayType,
        isEmailVerfied = isEmailVerfied
    )
}

fun createKakaoUserProfile(
    nickname: String = NICK_NAME,
    profileImageUrl: String = PROFILE_IMAGE,
    thumbnailImageUrl: String = THUMNAIL_IMAGE
): KakaoUserProfile {
    return KakaoUserProfile(
        nickname = nickname,
        profileImageUrl = profileImageUrl,
        thumbnailImageUrl = thumbnailImageUrl
    )
}