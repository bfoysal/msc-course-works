package com.uwindsor.comp8390.asynchronous.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;


/**
 *  @Author: Gidado Abdulrauf
 *  @Email: abdulraufgidado@yahoo.com, aag450@gmail.com
 */

@Getter @Setter
@Builder
@AllArgsConstructor
public class ProjectDto implements java.io.Serializable {

	private static final long serialVersionUID = 4943339380535322700L;
    /**
     * The fields below are extracted
     * from the Project Entity
	*/
	 private ZonedDateTime lastModified;
	 private  String projName;
	 private  Long projId;
	 private  String location;
	 private List<WorksOnDto> worksOn;

	public ProjectDto(){}

	@Override
	public String toString() {
		return "ProjectDto{" + 
			" lastModified= " + lastModified + 
			", projName= " + projName + 
			", projId= " + projId + 
			", location= " + location +
			", worksOn= " + worksOn +
				"}";
	}

		
} 