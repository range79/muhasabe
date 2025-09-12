package com.range.muhasebe.common.service

import com.range.muhasebe.common.exception.TokenException
import com.range.muhasebe.common.security.jwt.JWTUtil
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service

@Service
class JWTBlacklistService(
    private val jwtUtil: JWTUtil,
    private val redisTemplate: RedisTemplate<String, String>
)
{
    fun blacklistToken(jwtToken: String){
        try {

            val isActive =jwtUtil.validateToken(jwtToken)
            val jti = jwtUtil.getJti(jwtToken)
            if (!isActive){
                throw TokenException(null)
            }
            if (checkToken(jwtToken)){
              throw TokenException("Token Already blacklisted!")
            }
            val time = jwtUtil.calculateRemainingTime(jwtToken)

            redisTemplate.opsForValue().set(jti, "blacklisted", time)

        } catch (e: Exception) {
            throw TokenException(e.message ?: "Redis Error Occurred")
        }



    }
    fun checkToken(jwtToken: String): Boolean {
        try {
            val jti = jwtUtil.getJti(jwtToken)
            return redisTemplate.hasKey(jti)
        } catch (e: Exception) {
            throw TokenException(e.message ?: "Redis Error Occurred")
        }
    }
}