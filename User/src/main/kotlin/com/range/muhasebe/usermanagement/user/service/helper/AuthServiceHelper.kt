package com.range.muhasebe.usermanagement.user.service.helper

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component
import java.util.UUID
import java.util.concurrent.TimeUnit
@Component
class AuthServiceHelper(
    private val mailSender: JavaMailSender,
    private val redisTemplate: RedisTemplate<String, String>,
    )

{ private val logger = LoggerFactory.getLogger(AuthServiceHelper::class.java)
    @Value(value = "\${app.url}")
    private lateinit var  appUrl: String

    @Value(value = "\${api.prefix}")
    private lateinit var prefix: String;

    fun sendMail(email: String){
        val token: String? = UUID.randomUUID().toString()
        redisTemplate.opsForValue().set(
            "forgot:$token",
         email,
            15,
            TimeUnit.MINUTES
        )
        val resetUrl = "$appUrl$prefix/auth/reset-Password?token=$token"

        val message = SimpleMailMessage().apply {
            setFrom("Muhasebe <noreply.eloboostum@gmail.com>")
            setTo(email)
            subject = "Password Reset Request"
            text = buildString {
                appendLine("Hello,")
                appendLine()
                appendLine("You requested a password reset for your account.")
                appendLine("Click the link below to reset your password:")
                appendLine(resetUrl)
                appendLine()
                appendLine("If you did not request this, please ignore this email.")
                appendLine()
                appendLine("Best regards,")
                appendLine("The Muhasebe Team")
            }
        }

        mailSender.send(message)

    }
    fun getEmailAndConsumeToken(token: String): String? {
        val key = "forgot:$token"

        return try {
            val email = redisTemplate.opsForValue().get(key)
            if (!email.isNullOrEmpty()) {
                redisTemplate.delete(key)
                email
            } else {

                null
            }
        } catch (e: Exception) {
            logger.error("A problem occurred while retrieving and consuming the password reset token: ${e.message}")
            null
        }
    }
}