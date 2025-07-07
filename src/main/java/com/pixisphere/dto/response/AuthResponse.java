package com.pixisphere.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pixisphere.entity.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    private String email;
    private Role role;
}