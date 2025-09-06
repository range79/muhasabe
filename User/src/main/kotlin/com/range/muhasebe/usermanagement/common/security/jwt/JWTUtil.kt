package com.range.muhasebe.usermanagement.common.security.jwt

import com.range.muhasebe.usermanagement.common.security.details.CustomUserDetails
import com.range.muhasebe.usermanagement.user.domain.model.Role
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.Date
import java.util.UUID
import javax.crypto.SecretKey

@Component
class JWTUtil {
    @Value("\${jwt.secret}")
    private lateinit var secret: String

    @Value("\${jwt.duration}")
    private lateinit var duration: String
    fun generateToken(id: Long?, role: Role?): String {
        return Jwts
            .builder().subject(id.toString())
            .claim("role", role?.authority ?: Role.ROLE_USER).signWith(getSecretKey())
            .id(UUID.randomUUID().toString())
            .expiration(Date(System.currentTimeMillis() + duration.toLong() * 1000L))
            .compact()
    }

    fun getSecretKey(): SecretKey {
        return Keys
            .hmacShaKeyFor(
                secret
                    .toByteArray()
            )
    }

    fun parseToken(token: String?): Claims {
        return Jwts
            .parser()
            .verifyWith(getSecretKey())
            .build()
            .parseSignedClaims(token)
            .getPayload()
    }

    fun getUserId(token: String?): Long {
        val claims = parseToken(token)
        return claims.subject.toLong()
    }

    fun validateToken(token: String?, userDetails: UserDetails): Boolean {
        val claim = parseToken(token)
        val id = claim.subject.toLong()
        val expiration = claim.expiration

        return id == (userDetails as CustomUserDetails).getId() && !expiration.before(Date(System.currentTimeMillis()))
    }
    fun validateToken(token: String): Boolean {
        val claim = parseToken(token)
        val expiration = claim.expiration

        return  !expiration.before(Date(System.currentTimeMillis()))
    }
    fun getJti(jwtToken: String): String {
        val claims = parseToken(jwtToken)
        return claims.id.toString()
    }
    fun calculateRemainingTime(jwtToken: String): Long {
        val claims = parseToken(jwtToken)
        val expiration = claims.expiration
        val now =System.currentTimeMillis()
        val time = expiration.time-now
        return time
    }
}