/*

package com.uwindsor.comp8390.asynchronous.service.impl;

*/
/**
 * @Author: Gidado Abdulrauf
 * @Email: abdulraufgidado@yahoo.com
 *
 *//*


import com.uwindsor.comp8390.asynchronous.Exception.ModelNotFoundException;
import com.uwindsor.comp8390.asynchronous.domain.WorksOnId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;


@Service
@Transactional
@Slf4j
public class WorksOnIdServiceImpl implements WorksOnIdService {


    private WorksOnIdRepository repository;

    @Autowired
	    public WorksOnIdServiceImpl(WorksOnIdRepository repository) {
	        this.repository = repository;
	    }
	
	
	    @Override
	    public WorksOnIdRepository getRepository() {
	        return repository;
	    }
	
	    public WorksOnId findByIDWithRef(Long id) throws ModelNotFoundException {
	        if (repository.findById(id).isPresent()){
	            return repository.findById(id).get();
            }else throw new ModelNotFoundException(id.toString());
	    }




*/
/******************************
     * Queries and special methods ***************
     **************************************************************************//*





    @Override
    public WorksOnId getNewWithDefaults() {
        return new WorksOnId();
    }

    @Override
    public WorksOnIdDto entity2DTO(WorksOnId entity) {

        WorksOnIdDto dto
                = WorksOnIdDto
                .builder()
                .workHour(entity.getWorkHour())
                .projId(entity.getProjId())
                .empId(entity.getEmpId())
                .lastModified(entity.getLastModified())
                .deptId(entity.getDeptId())
                .date(entity.getDate())
                .build();
        return dto;

    }


		@Override
		public Collection<WorksOnIdDto> entities2DTOs(Collection<WorksOnId> entities) {
			Collection<WorksOnIdDto> dtos =
			entities.stream().map(this::entity2DTO).collect(Collectors.toCollection(ArrayList::new));
			return dtos;
        }

        @Override
        public WorksOnId dto2Entity(WorksOnIdDto dto) {


        WorksOnId one = repository.findOne(dto.getId());
        	return one;


			return null;
        }

    @Override
    public Collection<WorksOnId> dtos2Entities(Collection<WorksOnIdDto> dtos) {
        Collection<WorksOnId> entities =
                dtos.stream().map(this::dto2Entity).collect(Collectors.toList());
        return entities;
    }

}

*/
