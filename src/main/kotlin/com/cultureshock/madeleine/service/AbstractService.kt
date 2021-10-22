package com.cultureshock.madeleine.service

import org.slf4j.Logger
import retrofit2.Response

open class AbstractService {

    protected fun <T> isSuccessful(res: T, funName: String, logger: Logger): T {

        val exc = res
        if(res == null){
            logger.error("[Failed!!] ${funName} ==> ${exc.toString()}")
        }

        return exc
    }
}