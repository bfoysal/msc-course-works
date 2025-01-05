package com.uwindsor.comp8390.asynchronous;

import com.uwindsor.comp8390.asynchronous.domain.Project;
import com.uwindsor.comp8390.asynchronous.domain.WorksOn;
import com.uwindsor.comp8390.asynchronous.dto.ProjectDto;
import com.uwindsor.comp8390.asynchronous.service.DepartmentService;
import com.uwindsor.comp8390.asynchronous.service.EmployeeService;
import com.uwindsor.comp8390.asynchronous.service.ProjectService;
import com.uwindsor.comp8390.asynchronous.service.WorksOnService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Gidado Abdulrauf Aremu
 * @created_date: 30/03/20:9:26 AM
 * email:gidado@uwindsor.ca, abdulraufgidado@yahoo.com
 **/
@SpringBootTest
@Slf4j
public class ProjectServiceTest {

    private ProjectService projectService;
    private EmployeeService employeeService;
    private WorksOnService worksOnService;
    private DepartmentService departmentService;

    @Autowired
    public ProjectServiceTest(ProjectService projectService, EmployeeService employeeService,
                              WorksOnService worksOnService, DepartmentService departmentService) {
        this.projectService = projectService;
        this.employeeService = employeeService;
        this.worksOnService = worksOnService;
        this.departmentService = departmentService;
    }

    private List<Long> projectIds = new ArrayList<>();
    @Test
    @Transactional(readOnly = true)
    public void TestRetrieveProjectList(){

       List<Project> project = projectService.findAll();

        List<WorksOn> worksOnList = new ArrayList<>();

        for (Project p: project) {
            ProjectDto projectDto = ProjectDto.builder()
                    .projId(p.getProjId())
                    .lastModified(p.getLastModified())
                    .location(p.getLocation())
                    .projName(p.getProjName())
                    .build();
                projectIds.add(p.getProjId());

            //assert !(worksOnList.isEmpty());
            log.info("works on size", p.getWorksOns().size());

            worksOnList.addAll(p.getWorksOns());
            for (WorksOn workson: worksOnList
                 ) {

                    log.info("Works on for project with id {} is {}",p.getProjId(),workson.getWorksOnId());


            }


            log.info("from getOneMethod: {}",projectDto);

        }

       // log.info("List of all projects: {} ",projectService.getRepository().findById((long) 1));
    }

    @Test
    public void workOnTest(){
        List<WorksOn> worksOnList = worksOnService.getRepository().findAll();
        assert !worksOnList.isEmpty();
        log.info("Works on list size is {}",worksOnList.size());
    }

}
