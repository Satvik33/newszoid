package com.stackroute.loginservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {
	private String username;
	private String password;

	@Override
	public String toString() {
		return "UserDTO{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
