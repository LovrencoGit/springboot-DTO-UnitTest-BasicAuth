package com.certimetergroup.formazione.controller;

import java.util.ArrayList;

import com.certimetergroup.formazione.dto.DTOUser;
import com.certimetergroup.formazione.enumeration.ResponseErrorEnum;
import com.certimetergroup.formazione.exception.FailureException;
import com.certimetergroup.formazione.response.GetUsersResponse;
import com.certimetergroup.formazione.response.InsertNewUserResponse;
import com.certimetergroup.formazione.service.UserService;
import com.certimetergroup.formazione.utilities.UserUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.certimetergroup.formazione.model.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private UserService userService;
	
	

	@GetMapping
	public ResponseEntity<GetUsersResponse> getUsers() {
		logger.info("---------- GET /users ----------");
		
		ArrayList<User> users = userService.getUsers();
		
		return ResponseEntity.status(HttpStatus.OK).body(
			new GetUsersResponse(0, "SUCCESS", users)
		);
	}

	@PostMapping
	public ResponseEntity<InsertNewUserResponse> insertNewUser(@RequestBody DTOUser dtoUser) {	//XXX in input mi aspetto un DTOUser, non più uno User
		logger.info("---------- POST /users ----------");
		
		if( !UserUtility.isValidForInsert(dtoUser) ){		//XXX aggiunta (per scopi di esempio) la validazione dell'input "fatta a mano"
			logger.info("INPUT VALIDATION ERROR - dtoUser => " + dtoUser.toString());
			throw new FailureException(HttpStatus.BAD_REQUEST, ResponseErrorEnum.ERR_1);
		}
		
		User user = dtoUser.toUser();
		
		int numRowsInserted = userService.insertNewUserWithRowsInsertedCheck(user);
		logger.info("numRowsInserted => " + numRowsInserted);
		
		ArrayList<User> users = userService.getUsers();
		return ResponseEntity.status(HttpStatus.OK).body(
			new InsertNewUserResponse(0, "SUCCESS", user, users)
		);
	}
	
}
