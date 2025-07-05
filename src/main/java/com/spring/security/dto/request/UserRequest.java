package com.spring.security.dto.request;

import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @ToString
@Builder
public class UserRequest {

    private String name;
    private String email;
    private String phone;
    private String address;
    private Long pin;
}
