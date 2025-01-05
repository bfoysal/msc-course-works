package com.uwindsor.comp8390.asynchronous.domain;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Gidado Abdulrauf
 * @Email: abdulraufgidado@yahoo.com
 * @Created: 30-Mar-2020 2:32:04 AM
 */

@Entity
@Table(name="project") 
@Getter @Setter
@Builder
@AllArgsConstructor
public class Project implements Serializable{

	private static final long serialVersionUID = 1L;

     @Id 
    @Column(name="proj_id", unique=true, nullable=false)
    private Long projId;
 
    
    @Column(name="proj_name", length=50)
    private String projName;
 
    
    @Column(name="location", length=50)
    private String location;
 
    
    @Column(name="last_modified", nullable=false, length=19)
    private ZonedDateTime lastModified;
 
    @OneToMany(fetch=FetchType.LAZY, mappedBy="project")
    private Set<WorksOn> worksOns = new HashSet<WorksOn>(0);


    public Project() {
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


