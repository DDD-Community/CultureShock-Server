package com.cultureshock.madeleine.domain.performance

import com.cultureshock.madeleine.domain.AbstractBaseAuditEntity
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

        @Column(nullable= false)
        val mt20id: String,  //공연ID

        @Column(nullable = false)
        val prfnm: String,  //공연명

        @Column(nullable = false)
        val prfpdfrom: LocalDate,  //공연 시작일

        @Column(nullable = false)
        val prfpdto: LocalDate,  //공연 종료일

        @Column(nullable = false)
        val fcltynm: String,  //공연 시설명

        val poster: String,  //포스터이미지 URL

        @Column(nullable = false)
        var prfstate: String,  // 공연 상태 (1. 공연예정, 2.공연 중, 3.공연 완료)

        val genrenm: String  //공연 장르 명(ex. 연극)

        ) : AbstractBaseAuditEntity()

@Entity
@Table(name="performance_detail") //공연 상세
class PerformanceDetail(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long ,//seq

        @Column(nullable= false)
        val mt20id: String,      //공연ID

        @Column(name="mt10id", nullable= false)
        val mt10id: String,      //공연시설ID

        @Column(nullable= false)
        val prfnm: String,       //공연명

        @Column(nullable= false)
        val prfpdfrom: LocalDate,     //공연시작일

        @Column(nullable= false)
        val prfpdto: LocalDate,       //공연종료일

        val fcltynm: String? =null,    //공연시설명(공연장명)
        val prfcast: String? =null,    //공연출연진
        val prfcrew: String? =null,    //공연제작진
        val prfruntime: String? = null, //공연런타임

        var prfage: String = "전체이용가",//공연 관람 연령
        var entrpsnm: String? = null,   //제작사

        @Column(nullable= false)
        var pcseguidance: String,//티켓가격

        @Column(nullable= false)
        val poster: String,      //포스터 이미지 경로

        val sty: String? = null,         //줄거리
        val genrenm: String? = null,     //장르
        var prfstate: String? = null,    //공연상태
        var openrun: Char = 'N',        //오픈런

        val styurl_1: String? = null, // 소개이미지목록1
        val styurl_2: String? = null, // 소개이미지목록2
        val styurl_3: String? = null, // 소개이미지목록3
        val styurl_4: String? = null, // 소개이미지목록4

        var dtguidance: String? =null,   //공연시간

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "l_mt10id", referencedColumnName = "mt10id")
        val locationDetails : LocationDetail? = null//위치

): AbstractBaseAuditEntity()

@Entity
@Table(name = "performance_location_detail")
class LocationDetail(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val lid: Long,

        @Column(name="mt10id", nullable = false)
        val mt10id: String,              //공연시설ID

        @Column(nullable = false)
        val fcltynm: String,             //공연 시설 명

        var mt13cnt: Int? = null,        //공연장 수
        var fcltychartr: String? = null, //시설 특성(ex. 기타(공공))
        var opende: String? = null,      //개관 연도
        var seatscale: Int? = null,      //객석 수
        var telno: String? = null,       //전화번호
        var relateurl: String? = null,   //홈페이지
        var adres: String? = null,       //주소
        var la: Double? = null,          //위도
        var lo: Double? = null,           //경도

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "locationDetails")
        val performanceDetail: Set<PerformanceDetail>? = mutableSetOf()

): Serializable, AbstractBaseAuditEntity()