package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.rest.RestSupport
import com.cultureshock.madeleine.rest.dto.response.HealthCheckResponse
import io.swagger.annotations.Api
import org.springframework.boot.info.BuildProperties
import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.ZonedDateTime

@Api(tags = ["상태체크 api"])
@RestController
class HealthCheckController(
    private val buildProperties: BuildProperties,
    private val environment: Environment
) : RestSupport() {

    @GetMapping("/", "/health")
    fun healthCheck(): ResponseEntity<*> {
        return response(
            HealthCheckResponse(
                application = buildProperties.name,
                profiles = environment.activeProfiles.toList(),
                health = "OK",
                time = ZonedDateTime.now()
            )
        )
    }
}