package ru.rienel.shs.mobile.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import lombok.ToString;

@DatabaseTable(tableName = "Person")
@ToString(of = {"id", "name", "surname", "patronymic"})
public class Person {

	@DatabaseField(columnName = "id")
	private Long id;

	@DatabaseField(columnName = "name",canBeNull = false, width = 100)
	private String name;

	@DatabaseField(columnName = "surname",canBeNull = false, width = 100)
	private String surname;

	@DatabaseField(columnName = "patronymic", width = 100)
	private String patronymic;

	@DatabaseField(columnName = "address")
	private String address;

	public Person() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
