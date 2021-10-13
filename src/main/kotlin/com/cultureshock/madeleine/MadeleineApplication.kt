package com.cultureshock.madeleine

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MadeleineApplication

fun main(args: Array<String>) {
    runApplication<MadeleineApplication>(*args)
}
