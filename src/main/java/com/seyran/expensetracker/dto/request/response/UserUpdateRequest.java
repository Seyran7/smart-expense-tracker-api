package com.seyran.expensetracker.dto.request.response;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequest {
    @Email(message="Email must be valid")
    @NotBlank(message="Email is required")
    private String email;

    @Size(min=6,message ="Passwordmust be at least 6 characters")
    private String password;
}
