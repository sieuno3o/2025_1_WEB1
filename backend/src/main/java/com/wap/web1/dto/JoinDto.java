package com.wap.web1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JoinDto {

    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min =6, max =15,message ="Password must be between 6 and 15 characters.")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\W).*", message = "Password must contain at least one lowercase letter, one uppercase letter, and one special character.")
    private String password;

    @NotBlank(message = "Nickname cannot be empty")
    @Size(min = 1, max = 20, message = "Nickname must be between 1 and 20 characters")
    private String nickname;
}
