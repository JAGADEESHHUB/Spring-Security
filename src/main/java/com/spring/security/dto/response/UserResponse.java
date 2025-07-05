package com.spring.security.dto.response;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@Builder
public class UserResponse {
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String address;
}
