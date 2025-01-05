package com.uwindsor.comp8390.asynchronous.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.Date;


/**
 *  @Author: Gidado Abdulrauf
 *  @Email: abdulraufgidado@yahoo.com, aag450@gmail.com
 */

@Data
@Builder
@AllArgsConstructor
public class EmployeeDto implements java.io.Serializable {

	private static final long serialVersionUID = 2482430188427765880L;
    /**
     * The fields below are extracted
     * from the Employee Entity
	*/
	 private  String sex;
	 private  Long empId;
	 private  String FName;
	 private  String LName;
	 private ZonedDateTime lastModified;
	 private  String address;
	 private  Date BDate;

	public EmployeeDto(){}

	@Override
	public String toString() {
		return "EmployeeDto{" + 
			" sex= " + sex + 
			", empId= " + empId + 
			", FName= " + FName + 
			", LName= " + LName + 
			", lastModified= " + lastModified + 
			", address= " + address + 
			", BDate= " + BDate + 
		"}";
	}

		
} 