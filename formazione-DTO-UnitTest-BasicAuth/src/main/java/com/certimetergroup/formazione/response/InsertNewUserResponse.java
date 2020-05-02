package com.certimetergroup.formazione.response;

import java.util.ArrayList;

import com.certimetergroup.formazione.dto.DTOUser;
import com.certimetergroup.formazione.model.User;

//XXX nuova classe response aggiunta per la POST
public class InsertNewUserResponse extends Response {

	private DTOUser userInserted;
	private ArrayList<DTOUser> users;	
	private int size;
	
	public InsertNewUserResponse(int code, String message) {
		super(code, message);
		this.userInserted = null;
		this.users = null;
		this.size = 0;
	}

	public InsertNewUserResponse(int code, String message, User userInserted, ArrayList<User> users, int size) {
		super(code, message);
		this.userInserted = userInserted.toDTOUser();
		this.users = new ArrayList<DTOUser>();	//XXX il passaggio da model a DTO deve essere fatto qui, durante l'imbustamento, lasciando all'oscuro il Controller della logica di trasformazione
		users.forEach(user -> 
			this.users.add( user.toDTOUser() )
		);
		this.size = size;
	}
	public InsertNewUserResponse(int code, String message, User userInserted, ArrayList<User> users) {
		super(code, message);
		this.userInserted = userInserted.toDTOUser();
		this.users = new ArrayList<DTOUser>();	//XXX il passaggio da model a DTO deve essere fatto qui, durante l'imbustamento, lasciando all'oscuro il Controller della logica di trasformazione
		users.forEach(user -> 
			this.users.add( user.toDTOUser() )
		);
		this.size = users.size();
	}

	
	
	public DTOUser getUserInserted() {
		return userInserted;
	}

	public void setUserInserted(DTOUser userInserted) {
		this.userInserted = userInserted;
	}

	public ArrayList<DTOUser> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<DTOUser> users) {
		this.users = users;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	
	
	@Override
	public String toString() {
		return "InsertNewUserResponse {userInserted=" + userInserted + ", users=" + users + ", size=" + size + "}";
	}
	
}
