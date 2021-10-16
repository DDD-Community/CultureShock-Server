package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.MadeleineApplication
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(classes = arrayOf(MadeleineApplication::class), webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HealthControllerTest{
    @Autowired
    private val wac: WebApplicationContext? = null

    private var mvc: MockMvc? = null

    companion object {
        private val log: Logger = LoggerFactory.getLogger(SignInController::class.java)
    }


    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        log.debug("test setup >>>")
        mvc = MockMvcBuilders.webAppContextSetup(wac!!).build()
    }

    @Test
    @DisplayName("서버_상태체크")
    @Throws(Exception::class)
    fun `Health_Check_Test`(){
        this.mvc!!
            .perform(get("/health").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
            .andExpect(jsonPath("\$.data.application").value("madeleine"))
            .andExpect(jsonPath("\$.data.health").value("OK"))
            .andExpect(jsonPath("\$.data.time").isNotEmpty)
            .andExpect(jsonPath("timestamp").isNotEmpty)
            .andExpect(jsonPath("message").value("ok"))
    }
}