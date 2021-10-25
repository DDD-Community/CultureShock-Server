package com.cultureshock.madeleine.domain.performance

import com.cultureshock.madeleine.domain.AbstractBaseAuditEntity
import com.cultureshock.madeleine.domain.performance.enum.PerformKind
import com.cultureshock.madeleine.domain.performance.enum.PerformState
import com.cultureshock.madeleine.domain.user.Authority
import com.cultureshock.madeleine.domain.user.User
import com.cultureshock.madeleine.domain.user.enum.AuthorityName
import org.hibernate.bytecode.enhance.internal.javassist.PersistentAttributesHelper
import org.springframework.data.mapping.model.MutablePersistentEntity
import java.io.Serializable
import java.time.LocalDate

import java.util.*
import javax.persistence.*

@Entity
@Table(name="performance") //공연 목록
class Performance (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long, //seq

        @Column(name = "mt20id", nullable= false)
        val performId: String,  //공연ID

        @Column(name = "prfnm", nullable = false)
        val performName: String,  //공연명

        @Column(name = "prfpdfrom", nullable = false)
        val performStartDate: Date,  //공연 시작일

        @Column(name = "prfpdto", nullable = false)
        val performEndDate: Date,  //공연 종료일

        @Column(name ="fcltynm", nullable = false)
        val hallName: String,  //공연 시설명

        @Column(name = "poster")
        val posterUrl: String,  //포스터이미지 URL

        @Column(name = "prfstate", nullable = false)
        var performState: String,  // 공연 상태 (0.공연 중, 1. 공연예정, 2.공연 완료)

        @Column(name = "genrenm")
        val performKind: String,  //공연 장르 명(ex. 연극)

        ) : AbstractBaseAuditEntity()

@Entity
@Table(name="performance_detail") //공연 상세
class PerformanceDetail(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val pid: Long ,//seq

        @Column(name = "mt20id", nullable= false)
        val performId: String,      //공연ID

        @Column(name="mt10id", nullable= false)
        val hallId: String,      //공연시설ID

        @Column(name = "prfcast")
        val performCast: String? =null,    //공연출연진

        @Column(name = "prfcrew")
        val performCrew: String? =null,    //공연제작진

        @Column(name = "prfruntime")
        val performRuntime: String? = null, //공연런타임

        @Column(name = "prfage")
        var performAge: String = "전체이용가",//공연 관람 연령

        @Column(name = "entrpsnm")
        var enterName: String? = null,   //제작사

        @Column(name = "pcseguidance", nullable= false)
        var price: String,//티켓가격

        @Column(name = "styurl_1")
        val performPhotoUrl1: String? = null, // 소개이미지목록
        @Column(name = "styurl_2")
        val performPhotoUrl2: String? = null, // 소개이미지목록
        @Column(name = "styurl_3")
        val performPhotoUrl3: String? = null, // 소개이미지목록
        @Column(name = "styurl_4")
        val performPhotoUrl4: String? = null, // 소개이미지목록

        @Column(name = "dtguidance")
        var performTime: String? =null,   //공연시간

        @Column(name= "fcltynm")
        val hallName: String? =null,    //공연시설명(공연장명)

        @Column(name = "poster", nullable= false)
        val posterUrl: String,      //포스터 이미지 경로

        @Column(name = "genrenm")
        val performKind: String?,  //공연 장르 명(ex. 연극)

        @Column(name = "prfstate")
        var performState: String? = null,    //공연상태

        @Column(name = "prfnm", nullable = false)
        val performName: String,       //공연명

        @Column(name = "prfpdfrom", nullable = false)
        val performStartDate: Date,  //공연 시작일

        @Column(name = "prfpdto", nullable = false)
        val performEndDate: Date,  //공연 종료일

        @OneToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "locationHallId")
        val hallLocation : LocationDetail? = null, //위치
)

@Entity
@Table(name = "performance_location_detail")
class LocationDetail(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val lid: Long,

        @Column(name="mt10id", nullable = false)
        val hallId: String,              //공연시설ID

        @Column(name = "fcltynm",nullable = false)
        val hallName: String,             //공연 시설 명

        @Column(name = "relateurl")
        var hallUrl: String? = null,   //홈페이지

        @Column(name = "adres")
        var hallAddres: String? = null,  //주소

        var la: Double? = null,     //위도
        var lo: Double? = null,     //경도

): Serializable