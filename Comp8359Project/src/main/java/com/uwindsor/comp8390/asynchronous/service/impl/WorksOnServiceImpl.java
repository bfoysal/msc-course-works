package com.uwindsor.comp8390.asynchronous.service.impl;

import com.uwindsor.comp8390.asynchronous.Exception.ModelAlreadyExistException;
import com.uwindsor.comp8390.asynchronous.Exception.ModelNotFoundException;
import com.uwindsor.comp8390.asynchronous.domain.WorksOn;
import com.uwindsor.comp8390.asynchronous.dto.WorksOnDto;
import com.uwindsor.comp8390.asynchronous.repository.WorksOnRepository;
import com.uwindsor.comp8390.asynchronous.service.WorksOnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @Author: Gidado Abdulrauf
 * @Email: abdulraufgidado@yahoo.com
 *
 */
@Service
@Transactional
@Slf4j
public class WorksOnServiceImpl implements WorksOnService {

	    private WorksOnRepository repository;

	    @Autowired
	    public WorksOnServiceImpl(WorksOnRepository repository) {
	        this.repository = repository;
	    }



	@Override
	public WorksOn findById(Long id) throws ModelNotFoundException {
		log.info(" find WorksOn with id {} ", id);

		return repository.findById(id).get();
	}

	@Override
	public List<WorksOn> findAll() {
		log.info(" about fetching all WorksOn");
		return repository.findAll();

	}

	@Override
	public WorksOn deleteById(Long s) throws ModelNotFoundException {
		log.info(" delete WorksOn by id {} ", s);
		WorksOn worksOn= findById(s);
		repository.delete(worksOn);
		return worksOn;
	}

	@Override
	public WorksOn save(WorksOn entity) throws ModelAlreadyExistException, ModelNotFoundException {
		log.info(" about saving a WorksOn entity {} ", entity);
		return repository.save(entity);
	}

	@Override
	public WorksOn update(WorksOn updated) throws ModelNotFoundException {
		log.info(" about updating WorksOn {} ", updated);
		return repository.save(updated);
	}




	@Override
	    public WorksOnRepository getRepository() {
	        return repository;
	    }
	
	    /*public WorksOn findByIDWithRef(Long id) throws ModelNotFoundException {

	    	if (repository.findOne(id)!=null){
	    		return repository.findOne(id);
			}else throw new ModelNotFoundException(id.toString());

	    }*/
	
	    /******************************
	     * Queries and special methods ***************
	     **************************************************************************/
	    @Override
	    public WorksOn getNewWithDefaults() {
	        return new WorksOn();
	    }

		@Override
		public WorksOnDto entity2DTO(WorksOn entity) {

			WorksOnDto dto
			= WorksOnDto
			.builder()
                .departmentId(entity.getDepartment().getDeptId().toString())
                .projectId(entity.getProject().getProjId().toString())
                //.idId(entity.getWorksOnId())
                .employeeId(entity.getEmployee().getEmpId().toString())
				.build();
			return dto;

		}


		@Override
		public Collection<WorksOnDto> entities2DTOs(Collection<WorksOn> entities) {
			Collection<WorksOnDto> dtos =
			entities.stream().map(this::entity2DTO).collect(Collectors.toCollection(ArrayList::new));
			return dtos;
        }

        @Override
        public WorksOn dto2Entity(WorksOnDto dto) {
	       /* if (repository.findOne(dto.getIdId())) {
                WorksOn one = repository.findOne(dto.getIdId());
            }*/
        	return null;
        }

        @Override
        public Collection<WorksOn> dtos2Entities(Collection<WorksOnDto> dtos) {
            Collection<WorksOn> entities =
            dtos.stream().map(this::dto2Entity).collect(Collectors.toList());
            return entities;
		}

} 