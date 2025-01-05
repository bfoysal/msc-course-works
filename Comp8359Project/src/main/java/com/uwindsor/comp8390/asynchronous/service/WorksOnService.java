package com.uwindsor.comp8390.asynchronous.service;


import com.uwindsor.comp8390.asynchronous.Exception.ModelAlreadyExistException;
import com.uwindsor.comp8390.asynchronous.Exception.ModelNotFoundException;
import com.uwindsor.comp8390.asynchronous.domain.WorksOn;
import com.uwindsor.comp8390.asynchronous.dto.WorksOnDto;
import com.uwindsor.comp8390.asynchronous.repository.WorksOnRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;


/**
* @Author: Gidado Abdulrauf
* @Email: abdulraufgidado@yahoo.com
 * 
 */
@Transactional
public interface WorksOnService{

	WorksOn findById(Long id) throws ModelNotFoundException;

	List<WorksOn> findAll();

	WorksOn deleteById(Long s) throws ModelNotFoundException;

	WorksOn save(WorksOn entity) throws ModelAlreadyExistException, ModelNotFoundException;

	WorksOn update(WorksOn updated) throws ModelNotFoundException;

    WorksOnRepository getRepository();

	 WorksOn getNewWithDefaults();

	 WorksOnDto entity2DTO(WorksOn entity);

	 Collection<WorksOnDto> entities2DTOs(Collection<WorksOn> entities);

	 WorksOn dto2Entity(WorksOnDto dto);

     Collection<WorksOn> dtos2Entities(Collection<WorksOnDto> dtos);
} 