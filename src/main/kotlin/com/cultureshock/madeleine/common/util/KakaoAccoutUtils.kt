package com.cultureshock.madeleine.common.util

class KakaoAccountUtils {

    companion object {

        fun getUsernameByKakaoAccount(providerId: Long): String {
            return ProviderConstants.KAKAO + providerId
        }

        fun getPasswordByKakaoAccount(providerId: Long): String {
            return ProviderConstants.KAKAO + providerId
        }
    }
}