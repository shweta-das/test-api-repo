package com.synchrony.rest.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "id", length = 10)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "username", nullable = false, length = 50)
	private String username;

	@Column(name = "password", nullable = false, length = 50)
	private String password;

	@Override
	public String toString() {
		return "User{" + "userid=" + id + ", username='" + username + '\'' +
				", password ='" + password + + '\'' + '}';
	}
	
	public User(Long id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public User() {}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		User user = (User) o;

		if (!username.equals(user.username))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return username.hashCode();
	}

}
