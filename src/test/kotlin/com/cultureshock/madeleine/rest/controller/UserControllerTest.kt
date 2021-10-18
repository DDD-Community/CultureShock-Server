package com.cultureshock.madeleine.rest.controller

import com.cultureshock.madeleine.MadeleineApplication
import io.mockk.every
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(classes = [MadeleineApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {
    @Autowired
    private val wac: WebApplicationContext? = null

    private var mvc: MockMvc? = null

    companion object {
        private val log: Logger = LoggerFactory.getLogger(SignInController::class.java)
    }

    @BeforeEach
    @Throws(Exception::class)
    fun setUp() {
        UserControllerTest.log.debug("test setup >>>")
        mvc = MockMvcBuilders.webAppContextSetup(wac!!).build()
    }
    /*
    @Test
    @WithMockUser
    @Throws(Exception::class)
    fun `getUserInfo_Test`(){

        this.mvc!!
            .perform(MockMvcRequestBuilders.get("/api/v1/user").header("").accept(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
    }
    */
}