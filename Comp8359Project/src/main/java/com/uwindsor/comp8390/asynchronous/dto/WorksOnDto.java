package com.uwindsor.comp8390.asynchronous.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 *  @Author: Gidado Abdulrauf
 *  @Email: abdulraufgidado@yahoo.com, aag450@gmail.com
 */

@Builder
@AllArgsConstructor
@Getter @Setter
public class WorksOnDto implements java.io.Serializable {

	private static final long serialVersionUID = -635486358452768429L;
    /**
     * The fields below are extracted
     * from the WorksOn Entity
	*/
    private  String departmentId;
    private  String employeeId;
   //private WorksOnId idId;
    private String worksElasticId;
    private  String projectId;
    private Date date;
    private int workHour;

	public WorksOnDto(){}




		
} 