package apiManagerUser.dto;

import java.io.Serializable;

import apiManagerUser.domain.User;

public class UserView implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	private String login;
	
	public UserView() {
	}
	
	public UserView(User user) {
		id = user.getId();
		name = user.getName();
		email = user.getEmail();
		login = user.getLogin();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
}
