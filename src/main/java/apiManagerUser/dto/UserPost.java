package apiManagerUser.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class UserPost implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String name;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String login;
	@NotBlank
	private String pass;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
