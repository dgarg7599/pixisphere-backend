package com.pixisphere.controller;

import com.pixisphere.dto.request.LoginRequest;
import com.pixisphere.dto.request.OtpRequest;
import com.pixisphere.dto.request.SignupRequest;
import com.pixisphere.dto.response.ApiResponse;
import com.pixisphere.dto.response.AuthResponse;
import com.pixisphere.dto.response.SignupResponse;
import com.pixisphere.service.IAuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final IAuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<SignupResponse>> signup(@Valid @RequestBody SignupRequest request, HttpServletResponse response) {
        return authService.signup(request, response);
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<ApiResponse<AuthResponse>> verifyOtp(@Valid @RequestBody OtpRequest request, HttpServletResponse response) {
        return authService.verifyOtp(request, response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response) {
        return authService.login(request, response);
    }
}
