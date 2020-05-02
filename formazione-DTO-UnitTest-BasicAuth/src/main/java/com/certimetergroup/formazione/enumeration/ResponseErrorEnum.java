package com.certimetergroup.formazione.enumeration;

public enum ResponseErrorEnum {

	ERR_1  (1,"Empty/Invalid Field"),
	ERR_2  (2,"Database action error"),
	ERR_3  (3,"No Result Found"),
	ERR_4  (4,"Authentication failed - bad credentials"),

	ERR_500 (500,"UnexpectedError");

	
	public int id;
	public String description;
	
	
	ResponseErrorEnum(int id, String descrpiton){
		this.id=id;
		this.description = descrpiton;
	}
	
}
