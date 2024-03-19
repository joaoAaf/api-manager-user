package apiManagerUser.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Email;

public class UserMod implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	@Email
	private String email;
	private String pass;

	public UserMod() {
	}

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

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
