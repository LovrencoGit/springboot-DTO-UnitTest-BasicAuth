package com.certimetergroup.formazione.repository;

import java.util.ArrayList;

import com.certimetergroup.formazione.databasefake.DatabaseFake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.certimetergroup.formazione.model.User;

@Repository
public class UserRepository {

	final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DatabaseFake userMapper;
	
	
	
	public ArrayList<User> getUsers() {
		return userMapper.queryGetUsers();
	}
	
	public int insertNewUser(User user) {
		return userMapper.queryInsertNewUser(user);
	}

}
