package com.uwindsor.comp8390.asynchronous.service.impl;


import com.uwindsor.comp8390.asynchronous.Exception.ModelAlreadyExistException;
import com.uwindsor.comp8390.asynchronous.Exception.ModelNotFoundException;
import com.uwindsor.comp8390.asynchronous.domain.Department;
import com.uwindsor.comp8390.asynchronous.repository.DepartmentRepository;
import com.uwindsor.comp8390.asynchronous.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Author: Gidado Abdulrauf
 * @Email: abdulraufgidado@yahoo.com
 *
 */
@Service
@Transactional
@Slf4j
public class DepartmentServiceImpl implements DepartmentService {


	    private DepartmentRepository repository;


	 @Autowired
	    public DepartmentServiceImpl(DepartmentRepository repository) {
	     this.repository = repository;
	    }



	@Override
	public Department findById(Long id) throws ModelNotFoundException {
		log.info(" find department with id {} ", id);

		return repository.findById(id).get();
	}

	@Override
	public List<Department> findAll() {
		log.info(" about fetching all department");
		return repository.findAll();

	}

	@Override
	public Department deleteById(Long s) throws ModelNotFoundException {
		log.info(" delete transaction Log by id {} ", s);
		Department department= findById(s);
		repository.delete(department);
		return department;
	}

	@Override
	public Department save(Department entity) throws ModelAlreadyExistException, ModelNotFoundException {
		log.info(" about saving a department entity {} ", entity);
		return repository.save(entity);
	}

	@Override
	public Department update(Department updated) throws ModelNotFoundException {
		log.info(" about updating Department {} ", updated);
		return repository.save(updated);
	}




	    @Override
	    public DepartmentRepository getRepository() {
	        return repository;
	    }
	
	 /*   public Department findByIDWithRef(Long id) throws ModelNotFoundException {
	        if (repository.findById(id).isPresent()){
	            return repository.findById(id).get();
            }else
                throw new ModelNotFoundException(id.toString());
	    }
	*/
	    /******************************
	     * Queries and special methods ***************
	  /*   **************************************************************************//*
	    @Override
	    public Department getNewWithDefaults() {
	        return new Department();
	    }

		@Override
		public DepartmentDto entity2DTO(Department entity) {

			DepartmentDto dto
			= DepartmentDto
			.builder()
                	.deptId(entity.getDeptId())
                	.deptName(entity.getDeptName())
                	.lastModified(entity.getLastModified())
                .employeeId(entity.getEmployee().getEmpId().toString())
				.build();
			return dto;

		}


		@Override
		public Collection<DepartmentDto> entities2DTOs(Collection<Department> entities) {
			Collection<DepartmentDto> dtos =
			entities.stream().map(this::entity2DTO).collect(Collectors.toCollection(ArrayList::new));
			return dtos;
        }

        @Override
        public Department dto2Entity(DepartmentDto dto) throws ModelNotFoundException {
	        if (repository.findById(dto.getDeptId()).isPresent()){
	            return repository.findById(dto.getDeptId()).get();
            }else
        	throw new ModelNotFoundException(dto.getEmployeeId());
        }

        @Override
        public Collection<Department> dtos2Entities(Collection<DepartmentDto> dtos) {
            *//*Collection<Department> entities =
            dtos.stream().map(this::dto2Entity).collect(Collectors.toList());
            return entities;*//*
            return null;
		}*/

} 