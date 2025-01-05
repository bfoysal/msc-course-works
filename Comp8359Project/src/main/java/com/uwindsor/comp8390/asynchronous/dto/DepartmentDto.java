package com.uwindsor.comp8390.asynchronous.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.ZonedDateTime;


/**
 *  @Author: Gidado Abdulrauf
 *  @Email: abdulraufgidado@yahoo.com, aag450@gmail.com
 */

@Data
@Builder
@AllArgsConstructor
public class DepartmentDto implements java.io.Serializable {

	private static final long serialVersionUID = -8066540053941669889L;
    /**
     * The fields below are extracted
     * from the Department Entity
	*/
	 private  String deptName;
	 private ZonedDateTime lastModified;
	 private  Long deptId;
    private  String employeeId;

	public DepartmentDto(){}

	@Override
	public String toString() {
		return "DepartmentDto{" + 
			" deptName= " + deptName + 
			", lastModified= " + lastModified + 
			", deptId= " + deptId + 
            ", employeeId= " + employeeId + 
		"}";
	}

		
} 