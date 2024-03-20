package apiManagerUser.dto;

import jakarta.validation.constraints.Email;

public record UserMod(String name, @Email String email, String pass) {

}