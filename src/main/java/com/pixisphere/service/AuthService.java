package com.pixisphere.service;

import com.pixisphere.dto.request.LoginRequest;
import com.pixisphere.dto.request.OtpRequest;
import com.pixisphere.dto.request.SignupRequest;
import com.pixisphere.dto.response.ApiResponse;
import com.pixisphere.dto.response.AuthResponse;
import com.pixisphere.dto.response.SignupResponse;
import com.pixisphere.entity.AppUser;
import com.pixisphere.entity.Role;
import com.pixisphere.repository.UserRepository;
import com.pixisphere.security.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final OtpService otpService;

    @Override
    public ResponseEntity<ApiResponse<SignupResponse>> signup(SignupRequest request, HttpServletResponse response) {
        Optional<AppUser> existingUser = userRepository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            AppUser user = existingUser.get();
            SignupResponse signupResponse = new SignupResponse(user.getName(), user.getEmail());

            return ResponseEntity.badRequest().body(ApiResponse.<SignupResponse>builder()
                    .message("Email already registered")
                    .data(signupResponse)
                    .build());
        }


        AppUser user = AppUser.builder()
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole() == null ? Role.CLIENT : request.getRole())
                .isVerified(false)
                .build();

        userRepository.save(user);

        String otp = otpService.generateOtp(request.getEmail());
        System.out.println(" -> OTP for " + request.getEmail() + " is: " + otp);

        SignupResponse signupResponse = new SignupResponse(user.getName(), user.getEmail());
        return ResponseEntity.ok(ApiResponse.<SignupResponse>builder()
                .message("Signup successful, OTP sent to email.")
                .data(signupResponse)
                .build());

    }

    public ResponseEntity<ApiResponse<AuthResponse>> verifyOtp(OtpRequest request, HttpServletResponse response) {
        AppUser user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isValid = otpService.validateOtp(request.getEmail(), request.getOtp());

        if (!isValid) {
            return ResponseEntity.badRequest().body(ApiResponse.<AuthResponse>builder()
                    .message("Invalid or expired OTP")
                    .data(null)
                    .build());
        }

        user.setVerified(true);
        userRepository.save(user);

        String token = jwtService.generateToken(user.getEmail(), user.getId(), user.getRole());
        response.setHeader("Authorization", "Bearer " + token);

        AuthResponse authResponse = AuthResponse.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .build();

        return ResponseEntity.ok(ApiResponse.<AuthResponse>builder()
                .message("OTP verified successfully.")
                .data(authResponse)
                .build());
    }

    @Override
    public ResponseEntity<ApiResponse<AuthResponse>> login(LoginRequest request, HttpServletResponse response) {
        AppUser user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.isVerified()) {
            throw new RuntimeException("User not verified. Please verify OTP first.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail(), user.getId(), user.getRole());
        response.setHeader("Authorization", "Bearer " + token);

        AuthResponse res = AuthResponse.builder()
                .email(user.getEmail())
                .role(user.getRole())
                .build();

        return ResponseEntity.ok(ApiResponse.<AuthResponse>builder()
                .message("Login successful")
                .data(res)
                .build());
    }
}
