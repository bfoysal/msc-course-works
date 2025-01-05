package com.uwindsor.comp8390.asynchronous.service;


import com.uwindsor.comp8390.asynchronous.Exception.ModelAlreadyExistException;
import com.uwindsor.comp8390.asynchronous.Exception.ModelNotFoundException;
import com.uwindsor.comp8390.asynchronous.domain.Department;
import com.uwindsor.comp8390.asynchronous.repository.DepartmentRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
* @Author: Gidado Abdulrauf
* @Email: abdulraufgidado@yahoo.com
 * 
 */
@Transactional
public interface DepartmentService// extends GenericService<Department, Long>
 {

	Department findById(Long id) throws ModelNotFoundException;

	List<Department> findAll();

	Department deleteById(Long s) throws ModelNotFoundException;

	Department save(Department entity) throws ModelAlreadyExistException, ModelNotFoundException;

	Department update(Department updated) throws ModelNotFoundException;

	DepartmentRepository getRepository();
/*
	 Department getNewWithDefaults();

	 DepartmentDto entity2DTO(Department entity);

	 Collection<DepartmentDto> entities2DTOs(Collection<Department> entities);

	 Department dto2Entity(DepartmentDto dto) throws ModelNotFoundException;

     Collection<Department> dtos2Entities(Collection<DepartmentDto> dtos);*/
} 