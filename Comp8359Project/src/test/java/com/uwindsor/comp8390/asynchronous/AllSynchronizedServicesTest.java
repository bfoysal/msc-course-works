package com.uwindsor.comp8390.asynchronous;

import com.uwindsor.comp8390.asynchronous.Exception.ModelNotFoundException;
import com.uwindsor.comp8390.asynchronous.domain.Department;
import com.uwindsor.comp8390.asynchronous.domain.WorksOn;
import com.uwindsor.comp8390.asynchronous.dto.DepartmentDto;
import com.uwindsor.comp8390.asynchronous.dto.WorksOnDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: Gidado Abdulrauf Aremu
 * @created_date: 04/04/20:4:06 AM
 * email:gidado@uwindsor.ca, abdulraufgidado@yahoo.com
 **/

@SpringBootTest
@Slf4j
public class AllSynchronizedServicesTest extends AbstractBaseTest{


    /**
     * RDBMS TESTS
     */

    @Test
    public void getAllDept(){

        //List<DepartmentDto> departmentDtos = new ArrayList<>();
        List<Department> depts = departmentService.findAll();
        for (Department d:depts
             ) {
            DepartmentDto departmentDto = DepartmentDto.builder()
            .deptId(d.getDeptId())
            .deptName(d.getDeptName())
            //.employeeId(d.getEmployee().getEmpId().toString())
            .build();
            log.info("all depts are {}",departmentDto.toString());
        }
    }

    @Test
    public void getById() throws ModelNotFoundException {

        long startTime = System.nanoTime();
        Department department = departmentService.findById(1L);
        log.info("Query Execution time is {}", (double) (System.nanoTime() - startTime) /1000000 + "milliseconds");
        log.info("dept is {}", department.getDeptName());
    }


    @Test
    public void TestDeptESInsert() throws IOException {

        DepartmentDto departmentDto = DepartmentDto.builder()
                .deptId(2L)
                .deptName("Clothing")
                .employeeId(String.valueOf(2))
                //.lastModified(ZonedDateTime.now())
                .build();

        String response = xDepartmentConversionService.create(departmentDto);
        log.info("response is {}",response);
    }

    @Test
    public void testDepartmentSynchronize() throws IOException, ModelNotFoundException {

        xSynchronizeService.synchronizeDepartment();
    }

    @Test
    public void testEmployeeSynchronize() throws IOException, ModelNotFoundException {

        xSynchronizeService.synchronizeEmployee();
    }

    @Test
    public void testProjectSynchronize() throws IOException, ModelNotFoundException {

        xSynchronizeService.synchronizeProject();

    }


    @Test
    public void testWorksOnSynchronize() throws IOException, ModelNotFoundException {

        xSynchronizeService.synchronizeWorksOn();

    }


    @Test
    public void checkWorksOnWithMultipleId() {
        WorksOn worksOn = worksOnService.getRepository()
                .findByEmployeeEmpIdAndAndDepartmentDeptIdAndProjectProjId(8L, 3L, 15L);


        WorksOnDto w =
                WorksOnDto.builder()
                        .workHour(worksOn.getWorkHour().intValue())
                        .date(worksOn.getDate())
                        .departmentId(worksOn.getDepartment().getDeptId().toString())
                        .employeeId(worksOn.getEmployee().getEmpId().toString())
                        .projectId(worksOn.getProject().getProjId().toString())
                        .build();

        log.info("works on dto hour is {} ", w.getWorkHour());

    }



    @Test
    public void testGetWorksOnbyProject(){

        Set<WorksOn> worksOns = worksOnService.getRepository().findByProjectProjId(1L);
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


        log.info("testdto is {}", worksOnDtos.get(0));
    }
}






