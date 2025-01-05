package com.uwindsor.comp8390.asynchronous.service.custom;

import com.uwindsor.comp8390.asynchronous.dto.DepartmentDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;

/**
 * @author: Gidado Abdulrauf Aremu
 * @created_date: 03/04/20:11:26 PM
 * email:aag450@gmail.com, abdulraufgidado@yahoo.com
 **/
public interface XDepartmentConversionService {

    String create(@RequestBody DepartmentDto departmentDto) throws IOException;

    Map<String, Object> view(@PathVariable final String id);

    Map<String, Object> searchByDepartmentName(@PathVariable final String field);

    String update(@RequestBody DepartmentDto departmentDto) throws IOException;

    String delete(@PathVariable final String id);


}
