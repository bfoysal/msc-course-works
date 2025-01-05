package com.uwindsor.comp8390.asynchronous.service.impl;


import com.uwindsor.comp8390.asynchronous.Exception.ModelAlreadyExistException;
import com.uwindsor.comp8390.asynchronous.Exception.ModelNotFoundException;
import com.uwindsor.comp8390.asynchronous.domain.Employee;
import com.uwindsor.comp8390.asynchronous.repository.EmployeeRepository;
import com.uwindsor.comp8390.asynchronous.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


/**
 * @Author: Gidado Abdulrauf
 * @Email: abdulraufgidado@yahoo.com
 *
 */
@Service
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {


	    private EmployeeRepository repository;

        @Autowired
	    public EmployeeServiceImpl(EmployeeRepository repository) {
	        this.repository = repository;

	    }



    @Override
    public Optional<Employee> findById(Long id) throws ModelNotFoundException {
        log.info(" find employee with id {} ", id);

        return repository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        log.info(" about fetching all Employee");
        return repository.findAll();

    }

    @Override
    public Optional<Employee> deleteById(Long s) throws ModelNotFoundException {
        log.info(" delete Employee by id {} ", s);
        Optional<Employee> employee= findById(s);
        repository.delete(employee.get());
        return (employee);
    }

    @Override
    public Employee save(Employee entity) throws ModelAlreadyExistException, ModelNotFoundException {
        log.info(" about saving a Employee entity {} ", entity);
        return repository.save(entity);
    }

    @Override
    public Employee update(Employee updated) throws ModelNotFoundException {
        log.info(" about updating employee {} ", updated);
        return repository.save(updated);
    }





    @Override
	    public EmployeeRepository getRepository() {
	        return repository;
	    }
	
	    public Employee findByIDWithRef(Long id) throws ModelNotFoundException {
	        if (repository.getOne(id)!=null){
	            return repository.getOne(id);
            }else throw new ModelNotFoundException(id.toString());

	    }
	
	    /******************************
	     * Queries and special methods ***************
	     **************************************************************************/
/*
	    @Override
	    public Employee getNewWithDefaults() {
	        return new Employee();
	    }

		@Override
		public EmployeeDto entity2DTO(Employee entity) {

			EmployeeDto dto
			= EmployeeDto
			.builder()
                	.empId(entity.getEmpId())
                	.address(entity.getAddress())
                	.BDate(entity.getBDate())
                	.FName(entity.getFName())
                	.lastModified(entity.getLastModified())
                	.LName(entity.getLName())
                	.sex(entity.getSex())
				.build();
			return dto;

		}


		@Override
		public Collection<EmployeeDto> entities2DTOs(Collection<Employee> entities) {
			Collection<EmployeeDto> dtos =
			entities.stream().map(this::entity2DTO).collect(Collectors.toCollection(ArrayList::new));
			return dtos;
        }

       @Override
        public Employee dto2Entity(EmployeeDto dto) throws ModelNotFoundException {
	        try {
                if (repository.findOne(dto.getEmpId())!=null) {
                    return repository.findOne(dto.getEmpId());
                }else throw new ModelNotFoundException(dto.getEmpId().toString());

            }

                //throw new ModelNotFoundException(dto.getEmpId().toString());
            catch (ModelNotFoundException e) {
	            throw new ModelNotFoundException(e.getMessage());
            }
        }

        @Override
        public Collection<Employee> dtos2Entities(Collection<EmployeeDto> dtos) {
         */
/*   Collection<Employee> entities =
            dtos.stream().map(this::dto2Entity).collect(Collectors.toList());
            return entities;*//*

         return null;
		}
*/

} 