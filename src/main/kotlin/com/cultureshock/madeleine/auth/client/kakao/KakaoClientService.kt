package com.cultureshock.madeleine.auth.client.kakao

import com.cultureshock.madeleine.auth.client.kakao.dto.response.KakaoUserLogout
import com.cultureshock.madeleine.auth.client.kakao.dto.response.KakaoUserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface KakaoClientService {

    @GET("/v2/user/me")
    @Headers("content-type: application/x-www-form-urlencoded;charset=utf-8")
    fun getUserInfo(@Header("Authorization") token: String): Call<KakaoUserResponse>

    @POST("/v1/user/logout")
    @Headers("content-type: application/x-www-form-urlencoded;charset=utf-8")
    fun signOut(@Header("Authorization") token: String): Call<KakaoUserLogout>
}