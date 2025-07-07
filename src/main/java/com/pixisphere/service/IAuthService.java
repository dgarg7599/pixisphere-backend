package com.pixisphere.service;

import com.pixisphere.dto.request.LoginRequest;
import com.pixisphere.dto.request.OtpRequest;
import com.pixisphere.dto.request.SignupRequest;
import com.pixisphere.dto.response.ApiResponse;
import com.pixisphere.dto.response.AuthResponse;
import com.pixisphere.dto.response.SignupResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseEntity<ApiResponse<SignupResponse>> signup(SignupRequest request, HttpServletResponse response);

    ResponseEntity<ApiResponse<AuthResponse>> verifyOtp(OtpRequest request, HttpServletResponse response);

    ResponseEntity<ApiResponse<AuthResponse>> login(LoginRequest request, HttpServletResponse response);
}
