package com.teatime.api

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource

@RestController
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
class IndexController {
    @GetMapping(path=["/"])
    fun index() = "Hello Spring"
}