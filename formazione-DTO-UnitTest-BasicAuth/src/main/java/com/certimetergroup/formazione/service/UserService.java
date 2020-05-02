package com.certimetergroup.formazione.service;

import java.util.ArrayList;

import com.certimetergroup.formazione.exception.FailureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.certimetergroup.formazione.enumeration.ResponseErrorEnum;
import com.certimetergroup.formazione.model.User;
import com.certimetergroup.formazione.repository.UserRepository;

@Service
public class UserService {

	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	/*** READ ***/
	
	
	public ArrayList<User> getUsers() {
		return userRepository.getUsers();
	}
	

	
	/*** INSERT ***/
	
	
	public int insertNewUser(User user) {
		return userRepository.insertNewUser(user);
	}
	public int insertNewUserWithRowsInsertedCheck(User user) {
		int numRowsInserted = userRepository.insertNewUser(user);
		if( numRowsInserted==0 ) {
			logger.error("ERROR insert user failure - " + user.toString());
			throw new FailureException(
				HttpStatus.INTERNAL_SERVER_ERROR, 
				ResponseErrorEnum.ERR_2
			);
		}
		return numRowsInserted;
	}

}
