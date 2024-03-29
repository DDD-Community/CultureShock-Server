import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    kotlin("plugin.jpa") version "1.5.31"
    kotlin("kapt") version "1.3.61"
}

group = "com.cultureshock"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
val qeurydslVersion = "4.4.0"

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class) {
    kotlin.srcDir("$buildDir/generated/source/kapt/main")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("com.google.code.gson:gson:2.8.5")

    // cache
    //implementation("org.ehcache:ehcache:3.8.1")
    //implementation("org.springframework.boot:spring-boot-starter-cache:2.3.4.RELEASE")
    //implementation("javax.cache:cache-api:1.1.1")

    implementation ("org.springframework.boot:spring-boot-starter-security")

    // jsoup
    implementation("org.jsoup:jsoup:1.13.1")

    // JWT Token
    implementation ("io.jsonwebtoken:jjwt:0.9.1")

    // jackson
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("com.google.apis:google-api-services-people:v1-rev20201013-1.30.10")
    implementation("junit:junit:4.13.1")

    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("org.springframework.security:spring-security-test")
    testImplementation("io.mockk:mockk:1.9.3.kotlin12")
    testImplementation("com.ninja-squad:springmockk:3.0.1")

    // querydsl (추가 설정)
    implementation("com.querydsl:querydsl-jpa:$qeurydslVersion")
    kapt("com.querydsl:querydsl-apt:$qeurydslVersion:jpa")
    kapt("org.springframework.boot:spring-boot-configuration-processor")

    api("com.squareup.retrofit2:retrofit:2.6.0")
    api("com.squareup.retrofit2:converter-gson:2.6.0")
    api("io.reactivex.rxjava2:rxjava:2.2.19")
    api("com.squareup.retrofit2:adapter-rxjava:2.8.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

springBoot.buildInfo { properties { } }

