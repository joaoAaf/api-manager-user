package apiManagerUser.dto;

import java.io.Serializable;

import apiManagerUser.domain.User;

public class CredentialsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String login;
	private String pass;
	
	public CredentialsDTO() {
	}
	
	public CredentialsDTO(User user) {
		login = user.getLogin();
		pass = user.getPass();
	}


	public String getLogin() {
		return login;
	}

	public void setLogin(String name) {
		this.login = name;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass() {
		return pass;
	}
}
