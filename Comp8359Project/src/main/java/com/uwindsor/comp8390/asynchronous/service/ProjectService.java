package com.uwindsor.comp8390.asynchronous.service;


import com.uwindsor.comp8390.asynchronous.Exception.ModelAlreadyExistException;
import com.uwindsor.comp8390.asynchronous.Exception.ModelNotFoundException;
import com.uwindsor.comp8390.asynchronous.domain.Project;
import com.uwindsor.comp8390.asynchronous.repository.ProjectRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
* @Author: Gidado Abdulrauf
* @Email: abdulraufgidado@yahoo.com
 * 
 */
@Transactional
public interface ProjectService{

	Project findById(Long id) throws ModelNotFoundException;

	List<Project> findAll();

	Project deleteById(Long s) throws ModelNotFoundException;

	Project save(Project entity) throws ModelAlreadyExistException, ModelNotFoundException;

	Project update(Project updated) throws ModelNotFoundException;

    ProjectRepository getRepository();
/*
	 Project getNewWithDefaults();

	 ProjectDto entity2DTO(Project entity);

	 Collection<ProjectDto> entities2DTOs(Collection<Project> entities);

	 Project dto2Entity(ProjectDto dto);

     Collection<Project> dtos2Entities(Collection<ProjectDto> dtos);*/
} 