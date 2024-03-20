package apiManagerUser.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserPost(@NotBlank String name, @NotBlank @Email String email, @NotBlank String pass) {

}
