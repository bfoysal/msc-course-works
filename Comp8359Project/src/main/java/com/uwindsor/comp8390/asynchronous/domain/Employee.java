package com.uwindsor.comp8390.asynchronous.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @Author: Gidado Abdulrauf
 * @Email: abdulraufgidado@yahoo.com
 * @Created: 30-Mar-2020 2:32:04 AM
 */

@Entity
@Table(name="employee") 
@Getter @Setter
@Builder
@AllArgsConstructor
public class Employee implements Serializable{

	private static final long serialVersionUID = 1L;

     @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="emp_id", unique=true, nullable=false)
    private Long empId;
 
    
    @Column(name="f_name", length=50)
    private String FName;
 
    
    @Column(name="l_name", length=50)
    private String LName;
 
    @Temporal(TemporalType.DATE)
    @Column(name="b_date", length=10)
    private Date BDate;
 
    
    @Column(name="sex", length=50)
    private String sex;
 
    
    @Column(name="address", length=50)
    private String address;
 
    
    @Column(name="last_modified", nullable=false, length=19)
    private ZonedDateTime lastModified;
 
    @OneToMany(fetch=FetchType.LAZY, mappedBy="employee")
    private Set<WorksOn> worksOns = new HashSet<WorksOn>(0);
 
    @OneToMany(fetch=FetchType.LAZY, mappedBy="employee")
    private Set<Department> departments = new HashSet<Department>(0);


    public Employee() {
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


