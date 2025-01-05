package com.uwindsor.comp8390.asynchronous.service.impl;


import com.uwindsor.comp8390.asynchronous.Exception.ModelAlreadyExistException;
import com.uwindsor.comp8390.asynchronous.Exception.ModelNotFoundException;
import com.uwindsor.comp8390.asynchronous.domain.Project;
import com.uwindsor.comp8390.asynchronous.repository.ProjectRepository;
import com.uwindsor.comp8390.asynchronous.service.ProjectService;
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
@Slf4j
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {


	    private ProjectRepository repository;

	    @Autowired
	    public ProjectServiceImpl(ProjectRepository repository) {
	        this.repository = repository;
	    }



	@Override
	public Project findById(Long id) throws ModelNotFoundException {
		log.info(" find Project with id {} ", id);

		return repository.findById(id).get();
	}

	@Override
	public List<Project> findAll() {
		log.info(" about fetching all Project");
		return repository.findAll();

	}

	@Override
	public Project deleteById(Long s) throws ModelNotFoundException {
		log.info(" delete Project by id {} ", s);
		Project project= findById(s);
		repository.delete(project);
		return project;
	}

	@Override
	public Project save(Project entity) throws ModelAlreadyExistException, ModelNotFoundException {
		log.info(" about saving a Project entity {} ", entity);
		return repository.save(entity);
	}

	@Override
	public Project update(Project updated) throws ModelNotFoundException {
		log.info(" about updating project {} ", updated);
		return repository.save(updated);
	}



	@Override
	    public ProjectRepository getRepository() {
	        return repository;
	    }
	
	    public Project findByIDWithRef(Long id) throws ModelNotFoundException {

	        if (repository.findById(id).isPresent()){
	            return repository.findById(id).get();
            }else throw new ModelNotFoundException(id.toString());
	    }
	
	    /******************************
	     * Queries and special methods ***************
	     **************************************************************************/
/*	    @Override
	    public Project getNewWithDefaults() {
	        return new Project();
	    }

		@Override
		public ProjectDto entity2DTO(Project entity) {

			ProjectDto dto
			= ProjectDto
			.builder()
                	.projName(entity.getProjName())
                	.projId(entity.getProjId())
                	.lastModified(entity.getLastModified())
                	.location(entity.getLocation())
				.build();
			return dto;

		}

		@Override
		public Collection<ProjectDto> entities2DTOs(Collection<Project> entities) {
			Collection<ProjectDto> dtos =
			entities.stream().map(this::entity2DTO).collect(Collectors.toCollection(ArrayList::new));
			return dtos;
        }*/

       /* @Override
        public Project dto2Entity(ProjectDto dto) {
	        if (repository.findOne(dto.getProjId()) != null){
	            return repository.findOne(dto.getProjId());
            }else return null;
        }

        @Override
        public Collection<Project> dtos2Entities(Collection<ProjectDto> dtos) {
            Collection<Project> entities =
            dtos.stream().map(this::dto2Entity).collect(Collectors.toList());
            return entities;
		}*/

} 