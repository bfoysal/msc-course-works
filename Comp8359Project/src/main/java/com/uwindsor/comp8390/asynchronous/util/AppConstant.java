package com.uwindsor.comp8390.asynchronous.util;

/**
 * @author: Gidado Abdulrauf Aremu
 * @created_date: 04/04/20:12:10 AM
 * email:gidado@uwindsor.ca, abdulraufgidado@yahoo.com
 **/

public interface AppConstant {

    String INDEX_NAME = "database";

    interface DatabaseTableNames {


        String DEPARTMENT_TYPE = "department";
        String EMPLOYEE_TYPE = "employee";
        String PROJECT_TYPE = "project";
        String WORKS_ON_TYPE = "works_on";
    }

    /**
     *
     .field("dept_id", departmentDto.getDeptId())
     .field("dept_name", departmentDto.getDeptName())
     .field("mgr_id",departmentDto.getEmployeeId())
     */

    interface DepartmentTableFieldsName{
        String DEPT_ID= "dept_id";
        String DEPT_NAME="dept_name";
        String MANAGER_ID="mgr_id";
        String LAST_MODIFIED_TIME = "last_modified";
    }


    interface EmployeeTableFieldsName{
        String EMPL_ID= "emp_id";
        String FIRST_NAME = "f_name";
        String LAST_NAME = "l_name";
        String DATE_OF_BIRTH = "b_date";
        String SEX = "sex";
        String ADDRESS="address";
    }

    /**
     *  private  String departmentId;
     private  String employeeId;
     private WorksOnId idId;
     private String worksElasticId = UUID.randomUUID().toString();
     private  String projectId;
     private EmployeeDto employeeDto;
     */

    interface WorksOnTableFieldName{
        String EMPL_ID= "emp_id";
        String WORKS_ON_ID = "works_on_id";
        String PROJECT_ID = "proj_id";
        String DEPT_ID = "dept_id";
        String WORK_HOUR = "work_hour";
        String DATE = "date";
    }

    interface ProjectTableFieldName{
        String PROJECT_NAME= "proj_name";
        String LOCATION = "location";
        String PROJECT_ID = "proj_id";
        String WORKS_ON = "works_on";
    }

}
