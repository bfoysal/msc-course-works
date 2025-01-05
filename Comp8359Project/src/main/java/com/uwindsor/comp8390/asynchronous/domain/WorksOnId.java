package com.uwindsor.comp8390.asynchronous.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * @Author: Gidado Abdulrauf
 * @Email: abdulraufgidado@yahoo.com
 * @Created: 30-Mar-2020 2:32:04 AM
 */

@Embeddable
@Getter @Setter
@Builder
@AllArgsConstructor

public class WorksOnId implements Serializable{

	private static final long serialVersionUID = 1L;

 

    @Column(name="emp_id")
    private Long empId;
 

    @Column(name="dept_id")
    private Long deptId;
 

    @Column(name="proj_id")
    private Long projId;



    public WorksOnId() {
    }

}


