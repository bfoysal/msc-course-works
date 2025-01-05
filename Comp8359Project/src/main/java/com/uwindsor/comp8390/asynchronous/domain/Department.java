package com.uwindsor.comp8390.asynchronous.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * @Author: Gidado Abdulrauf
 * @Email: abdulraufgidado@yahoo.com
 * @Created: 30-Mar-2020 2:32:04 AM
 */

@Entity
@Table(name="department") 
@Getter @Setter
@Builder
@AllArgsConstructor
public class Department implements Serializable{

	private static final long serialVersionUID = 1L;

     @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="dept_id", unique=true, nullable=false)
    private Long deptId;
 
    @ManyToOne(fetch=FetchType.LAZY)    
    @JoinColumn(name="mgr_id")
    private Employee employee;
 
    
    @Column(name="dept_name", length=50)
    private String deptName;
 
    
    @Column(name="last_modified", nullable=false, length=19)
    private ZonedDateTime lastModified;


    @OneToMany(fetch=FetchType.LAZY, mappedBy="department")
    private Set<WorksOn> worksOns = new HashSet<WorksOn>(0);


    public Department() {
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


