package com.uwindsor.comp8390.asynchronous.repository;


import com.uwindsor.comp8390.asynchronous.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @Author: Gidado Abdulrauf
 * @Email: abdulraufgidado@yahoo.com
 * 
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //Optional<Employee> findByEmpId(Long id);
} 