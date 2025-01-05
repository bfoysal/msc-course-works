package com.uwindsor.comp8390.asynchronous.schedulller;

import com.uwindsor.comp8390.asynchronous.Exception.ModelNotFoundException;
import com.uwindsor.comp8390.asynchronous.domain.Department;
import com.uwindsor.comp8390.asynchronous.domain.Employee;
import com.uwindsor.comp8390.asynchronous.domain.Project;
import com.uwindsor.comp8390.asynchronous.domain.WorksOn;
import com.uwindsor.comp8390.asynchronous.dto.DepartmentDto;
import com.uwindsor.comp8390.asynchronous.dto.EmployeeDto;
import com.uwindsor.comp8390.asynchronous.dto.ProjectDto;
import com.uwindsor.comp8390.asynchronous.dto.WorksOnDto;
import com.uwindsor.comp8390.asynchronous.service.DepartmentService;
import com.uwindsor.comp8390.asynchronous.service.EmployeeService;
import com.uwindsor.comp8390.asynchronous.service.ProjectService;
import com.uwindsor.comp8390.asynchronous.service.WorksOnService;
import com.uwindsor.comp8390.asynchronous.service.custom.XDepartmentConversionService;
import com.uwindsor.comp8390.asynchronous.service.custom.XEmployeeConversionService;
import com.uwindsor.comp8390.asynchronous.service.custom.XProjectConversionService;
import com.uwindsor.comp8390.asynchronous.service.custom.XWorksOnConversionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: Gidado Abdulrauf Aremu
 * @created_date: 06/04/20:10:28 AM
 * email:gidado@uwindsor.ca, abdulraufgidado@yahoo.com
 **/
@Slf4j
@Component
public class XSynchronizeService {

    private DepartmentService departmentService;

    private XDepartmentConversionService xDepartmentConversionService;

    private XEmployeeConversionService xEmployeeConversionService;

    private EmployeeService employeeService;

    private ProjectService projectService;

    private XProjectConversionService xProjectConversionService;

    private WorksOnService worksOnService;
    private XWorksOnConversionService xWorksOnConversionService;


    @Autowired
    public XSynchronizeService(DepartmentService departmentService, XDepartmentConversionService xDepartmentConversionService,
                               XEmployeeConversionService xEmployeeConversionService, EmployeeService employeeService,
                               ProjectService projectService, XProjectConversionService xProjectConversionService,
                               WorksOnService worksOnService, XWorksOnConversionService xWorksOnConversionService) {

        this.departmentService = departmentService;
        this.xDepartmentConversionService = xDepartmentConversionService;
        this.xEmployeeConversionService = xEmployeeConversionService;
        this.employeeService = employeeService;
        this.projectService = projectService;
        this.xProjectConversionService =xProjectConversionService;
        this.worksOnService = worksOnService;
        this.xWorksOnConversionService = xWorksOnConversionService;
    }

    /**
     * get all department
     * check last modified time
     * if more than current time then build elasticsearch payload for all more than current time
     * synchronize all built payload above
     * else do nothing but check again within specified interval
     */

    public void synchronizeDepartment() throws IOException, ModelNotFoundException {
        List<Department> allDepartments = departmentService.findAll();
        for (Department department: allDepartments) {
            if (department.getLastModified().isBefore(ZonedDateTime.now())){
                log.info("currently synchronizing department with id {}",department.getDeptId());
                xDepartmentConversionService.create(DepartmentDto.builder()
                        .employeeId(department.getEmployee().getEmpId().toString())
                        .deptName(department.getDeptName())
                        .deptId(department.getDeptId())
                        .build());
                department.setLastModified(ZonedDateTime.now());
                departmentService.update(department);
            }
        }
    }

    /**
     * get all employee
     * check last modified time
     * if more than current time then build elasticsearch payload for all more than current time
     * synchronize all built payload above
     * else do nothing but check again within specified interval
     */

    public void synchronizeEmployee() throws IOException, ModelNotFoundException {
        List<Employee> employees = employeeService.findAll();
        for (Employee employee: employees) {

            if (employee.getLastModified().isBefore(ZonedDateTime.now())){

                log.info("currently synchronizing employee with id {}", employee.getEmpId());
                xEmployeeConversionService.create(EmployeeDto.builder()
                .empId(employee.getEmpId())
                .address(employee.getAddress())
                .BDate(employee.getBDate())
                .FName(employee.getFName())
                .LName(employee.getLName())
                .sex(employee.getSex())
                .build());


                employee.setLastModified(ZonedDateTime.now());
                employeeService.update(employee);
            }
        }
    }


    /**
     * get all project
     * get all works on by that department id
     * check last modified time
     * if more than current time then build elasticsearch payload for all more than current time
     * synchronize all built payload above
     * else do nothing but check again within specified interval
     */

    public void synchronizeProject() throws IOException, ModelNotFoundException {
        List<Project> projects = projectService.findAll();

        for (Project project: projects) {
            if (project.getLastModified().isBefore(ZonedDateTime.now())){

                log.info("currently synchronizing project with id {}", project.getProjId());

                Set<WorksOn> worksOns = worksOnService.getRepository().findByProjectProjId(project.getProjId());

                List<WorksOnDto> worksOnDtos = new ArrayList<>();

                for (WorksOn w:worksOns
                        ) {

                    String worksOnElasticId = w.getEmployee().getEmpId()+"_"+w.getDepartment().getDeptId()+"_"+w.getProject().getProjId();

                    WorksOnDto testDto = WorksOnDto.builder()
                            .worksElasticId(worksOnElasticId)
                            .departmentId(w.getDepartment().getDeptId().toString())
                            .employeeId(w.getEmployee().getEmpId().toString())
                            .projectId(w.getProject().getProjId().toString())
                            .workHour(w.getWorkHour().shortValue())
                            .date(w.getDate())
                            .build();
                    worksOnDtos.add(testDto);
                }



                xProjectConversionService.create(ProjectDto.builder()
                .projId(project.getProjId())
                .projName(project.getProjName())
                .location(project.getLocation())
                .worksOn(worksOnDtos)
                .build());

                project.setLastModified(ZonedDateTime.now());
                projectService.update(project);
            }
        }
    }

    /**
     * get all works on
     * check last modified time
     * if more than current time then build elasticsearch payload for all more than current time
     * synchronize all built payload above
     * else do nothing but check again within specified interval
     */

    public void synchronizeWorksOn() throws IOException, ModelNotFoundException {

        List<WorksOn> worksOns = worksOnService.findAll();

        for (WorksOn worksOn: worksOns) {

            if (worksOn.getLastModified().isBefore(ZonedDateTime.now())){

                log.info("currently synchronizing workson with id {}",worksOn.getWorksOnId());

                xWorksOnConversionService.create(WorksOnDto.builder()
                        .employeeId(worksOn.getEmployee().getEmpId().toString())
                        .projectId(worksOn.getProject().getProjId().toString())
                        .departmentId(worksOn.getDepartment().getDeptId().toString())
                        .date(worksOn.getDate())
                        .workHour(worksOn.getWorkHour().intValue())
                        .worksElasticId(worksOn.getWorksOnId().toString())
                        .build());

                WorksOn wokrksOnToUpdate = worksOnService.getRepository()
                        .findByEmployeeEmpIdAndAndDepartmentDeptIdAndProjectProjId(worksOn.getEmployee().getEmpId(),
                                worksOn.getDepartment().getDeptId(),worksOn.getProject().getProjId());

                wokrksOnToUpdate.setLastModified(ZonedDateTime.now());
                worksOnService.update(wokrksOnToUpdate);
            }

        }


    }
}
