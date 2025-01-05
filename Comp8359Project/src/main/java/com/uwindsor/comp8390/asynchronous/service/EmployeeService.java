package com.uwindsor.comp8390.asynchronous.service;


import com.uwindsor.comp8390.asynchronous.Exception.ModelAlreadyExistException;
import com.uwindsor.comp8390.asynchronous.Exception.ModelNotFoundException;
import com.uwindsor.comp8390.asynchronous.domain.Employee;
import com.uwindsor.comp8390.asynchronous.repository.EmployeeRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * @Author: Gidado Abdulrauf
 * @Email: abdulraufgidado@yahoo.com
 *
 */

@Transactional
public interface EmployeeService //extends GenericService<Employee, Long>
{

	Optional<Employee> findById(Long id) throws ModelNotFoundException;

	List<Employee> findAll();

	Optional<Employee> deleteById(Long s) throws ModelNotFoundException;

	Employee save(Employee entity) throws ModelAlreadyExistException, ModelNotFoundException;

	Employee update(Employee updated) throws ModelNotFoundException;

	EmployeeRepository getRepository();/*
	 Employee getNewWithDefaults();

	 EmployeeDto entity2DTO(Employee entity);

	 Collection<EmployeeDto> entities2DTOs(Collection<Employee> entities);

	 Employee dto2Entity(EmployeeDto dto) throws ModelNotFoundException;

     Collection<Employee> dtos2Entities(Collection<EmployeeDto> dtos);*/
}
