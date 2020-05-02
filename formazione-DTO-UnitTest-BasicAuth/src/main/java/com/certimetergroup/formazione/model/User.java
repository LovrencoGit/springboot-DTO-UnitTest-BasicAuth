package com.certimetergroup.formazione.model;

import com.certimetergroup.formazione.dto.DTOUser;
import com.certimetergroup.formazione.utilities.DateUtility;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.Objects;

public class User {

	private Integer idUser;
	private String firstname;
	private String lastname;
	private LocalDate birthday;
	private String username;
	private String password;

	public User() {
	}
	public User(Integer idUser, String firstname, String lastname, LocalDate birthday, String username, String password) {
		this.idUser = idUser;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.username = username;
		this.password = password;
	}

	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public LocalDate getBirthday() {
		return birthday;
	}
	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
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

	@Override
	public String toString() {
		return "User{" +
				"idUser=" + idUser +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", birthday=" + birthday +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(idUser, user.idUser) &&
				Objects.equals(firstname, user.firstname) &&
				Objects.equals(lastname, user.lastname) &&
				Objects.equals(birthday, user.birthday) &&
				Objects.equals(username, user.username) &&
				Objects.equals(password, user.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUser, firstname, lastname, birthday, username, password);
	}
/*************************************************************************************************/
	/*************************************************************************************************/
	/*************************************************************************************************/

	
	//XXX nuovo metodo che ho aggiunto al model per il passaggo al DTO corrispondente
	public DTOUser toDTOUser() {
		DTOUser output = new DTOUser();						// 'this' 	=> User				'output'	=> DTOUser
		BeanUtils.copyProperties(this, output);		//vengono settate in 'output' tutti campi che hanno lo stesso nome tra la classe User e DTOUser

		/*** 'age' of DTO class is a value calculated from 'birthday' ***/
		Integer age = DateUtility.calculateAgeOf( this.getBirthday() );
		output.setAge(age);

		return output;
	}
}
