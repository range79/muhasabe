package com.range.muhasebe.userManagement.user.service.helper

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component
import java.security.SecureRandom
import java.util.UUID
import java.util.concurrent.TimeUnit
@Component
class AuthServiceHelper(
    private val mailSender: JavaMailSender,
    private val redisTemplate: RedisTemplate<String, String>,

    )
{

    private val secureRandom = SecureRandom()
    private val logger = LoggerFactory.getLogger(AuthServiceHelper::class.java)
    @Value(value = "\${app.url}")
    private lateinit var  appUrl: String

    @Value(value = "\${api.prefix}")
    private lateinit var prefix: String;

    fun passwordResetRequest(email: String){
        val token: String? = UUID.randomUUID().toString()
        redisTemplate.opsForValue().set(
            "forgot:$token",
         email,
            15,
            TimeUnit.MINUTES
        )
        val resetUrl = "$appUrl$prefix/auth/reset-Password?token=$token"

        val message = SimpleMailMessage().apply {
            from = "Muhasebe <noreply.eloboostum@gmail.com>"
            to(email)
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

        return checkToken(key)
    }

    fun twoFactoryAuth(email: String){
        val token: Int =    secureRandom.nextInt(1_000_000)
        val key ="%06d".format(token)




        redisTemplate.opsForValue().set(
            "2FA:$key",
            email,
            5,
            TimeUnit.MINUTES
        )


        val message = SimpleMailMessage().apply {
            from = "Muhasebe <noreply.eloboostum@gmail.com>"
            to(email)
            subject = "Your 2FA Verification Code"
            text = buildString {
                appendLine("Hello,")
                appendLine()
                appendLine("You requested to log in to your account using 2FA.")
                appendLine("Here is your verification code:")
                appendLine()
                appendLine("👉 $key")
                appendLine()
                appendLine("This code will expire in 5 minutes.")
                appendLine()
                appendLine("If you did not request this, please ignore this email.")
                appendLine()
                appendLine("Best regards,")
                appendLine("The Muhasebe Team")
            }
        }


        mailSender.send(message)

    }


    fun twoFactoryAuthChecker(token: String): String? {
        val key = "2FA:$token"

        return checkToken(key)
    }
    
    
    fun checkToken(key: String):String?{
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