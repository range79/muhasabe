package com.range.muhasebe.userManagement.user.controller



import com.range.muhasebe.userManagement.user.api.AuthApi
import com.range.muhasebe.userManagement.user.dto.LoginRequest
import com.range.muhasebe.userManagement.user.dto.RegisterRequest
import com.range.muhasebe.userManagement.user.service.AuthService
import org.springframework.web.bind.annotation.RestController

@RestController
class AuthController(private val authService: AuthService): AuthApi {
    //    @Value("\${jwt.duration}")
//    private var jwtDuration: Int = 0
//    @Value("\${https.enable}")
//    private var httpEnable: Boolean = false
    override fun login(loginRequest: LoginRequest): String? {
        return authService.login(loginRequest)
    }

    override fun register(registerRequest: RegisterRequest): String {
        return authService.register(registerRequest)
    }

    override fun forgotPasswordRequest(email: String) {
        return authService.forgotPasswordMailSender(email)
    }

    override fun resetPassword(token: String, password: String) {
        return authService.forgotPassword(token,password)
    }

    override fun login2FA(token: String) : String{
        return authService.twoFactorAuthentication(token)
    }


//    override fun login(loginRequest: LoginRequest): ResponseEntity<Void> {
//
//
//        val token = authService.login(loginRequest)
//
//        val cookie = ResponseCookie.from("jwt", token)
//            .httpOnly(true)
//            .secure(httpEnable)
//            .path("/")
//            .maxAge(jwtDuration.toLong())
//            .sameSite("Strict")
//            .build()
//
//        return ResponseEntity.ok()
//            .header("Set-Cookie", cookie.toString())
//            .build()
//    }
//
//    override fun register(registerRequest: RegisterRequest): ResponseEntity<Void> {
//        val token = authService.register(registerRequest)
//
//        val cookie = ResponseCookie.from("jwt", token)
//            .httpOnly(true)
//            .secure(httpEnable)
//            .path("/")
//            .maxAge(jwtDuration.toLong())
//            .sameSite("Strict")
//            .build()
//
//        return ResponseEntity.ok()
//            .header("Set-Cookie", cookie.toString())
//            .build()
//    }
}