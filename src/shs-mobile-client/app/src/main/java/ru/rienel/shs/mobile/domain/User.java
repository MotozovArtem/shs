package ru.rienel.shs.mobile.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.ToString;

@DatabaseTable(tableName = "User")
@ToString(of = {"id", "username", "person"})
public class User {

	@DatabaseField(columnName = "id", id = true)
	private Long id;

	@DatabaseField(columnName = "username")
	private String username;

	@DatabaseField(columnName = "password")
	private String password;

	@DatabaseField(columnName = "person", foreign = true, foreignAutoRefresh = true)
	private Person person;

	public User() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
