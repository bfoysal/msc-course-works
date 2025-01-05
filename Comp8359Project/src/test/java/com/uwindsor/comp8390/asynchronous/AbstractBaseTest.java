package com.uwindsor.comp8390.asynchronous;

import com.uwindsor.comp8390.asynchronous.schedulller.XSynchronizeService;
import com.uwindsor.comp8390.asynchronous.service.DepartmentService;
import com.uwindsor.comp8390.asynchronous.service.WorksOnService;
import com.uwindsor.comp8390.asynchronous.service.custom.XDepartmentConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: Gidado Abdulrauf Aremu
 * @created_date: 05/04/20:8:15 PM
 * email:gidado@uwindsor.ca, abdulraufgidado@yahoo.com
 **/
@SpringBootTest(classes = AsynchronousApplication.class)
public abstract class AbstractBaseTest {


    public AbstractBaseTest() { }

    @Autowired
    protected XDepartmentConversionService xDepartmentConversionService;

    @Autowired
    protected DepartmentService departmentService;

    @Autowired
    protected XSynchronizeService xSynchronizeService;

    @Autowired
    protected WorksOnService worksOnService;



}
