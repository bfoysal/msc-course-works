package com.uwindsor.comp8390.asynchronous.repository;


import com.uwindsor.comp8390.asynchronous.domain.WorksOn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;


/**
 * @Author: Gidado Abdulrauf
 * @Email: abdulraufgidado@yahoo.com
 * 
 */
public interface WorksOnRepository extends JpaRepository<WorksOn, Long> {

    @Query("SELECT w FROM WorksOn w WHERE w.project.projId = ?1")
    Set<WorksOn> findByProjectProjId(Long id);


    @Query("SELECT w FROM WorksOn w WHERE w.employee.empId =?1 and w.department.deptId =?2 and w.project.projId = ?3")
    WorksOn findByEmployeeEmpIdAndAndDepartmentDeptIdAndProjectProjId(Long empId, Long deptId, Long projId);

} 