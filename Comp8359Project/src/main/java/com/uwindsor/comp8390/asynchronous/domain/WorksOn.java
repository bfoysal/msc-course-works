package com.uwindsor.comp8390.asynchronous.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;

/**
 * @Author: Gidado Abdulrauf
 * @Email: abdulraufgidado@yahoo.com
 * @Created: 30-Mar-2020 2:32:04 AM
 */

@Entity
@Table(name="works_on")
@Getter @Setter
@Builder
@AllArgsConstructor
public class WorksOn implements Serializable{

	private static final long serialVersionUID = 1L;

     @Id
    private WorksOnId worksOnId;
 
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="dept_id", insertable=false, updatable=false)
    private Department department;
 
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="emp_id", insertable=false, updatable=false)
    private Employee employee;
 
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="proj_id", insertable=false, updatable=false)
    private Project project;


    @Column(name="work_hour", length=50, insertable=false, updatable=false)
    private Long workHour;

    @Column(name="date", length=50, insertable=false, updatable=false)
    private Date date;




	@Column(name="last_modified", nullable=false, length=19, insertable = false, updatable = false)
	private ZonedDateTime lastModified;

    public WorksOn() {
    }



    /**
    * This is called as an event before the persist
    * action is called by the entity manager
    */
    @PrePersist
    public void beforeEntityIsSaved() {
		setLastModified(ZonedDateTime.now());
		//id = ID.generateUUIDString();
    }

    /**
    * This is called as an event before the update of merge
    * action is called by the entity manager
    */
    @PreUpdate
    public void beforeEntityUpdated() {
    	setLastModified(ZonedDateTime.now());
    }
}


