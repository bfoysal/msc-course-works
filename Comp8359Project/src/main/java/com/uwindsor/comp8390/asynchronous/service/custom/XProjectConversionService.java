package com.uwindsor.comp8390.asynchronous.service.custom;

import com.uwindsor.comp8390.asynchronous.dto.ProjectDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;

/**
 * @author: Gidado Abdulrauf Aremu
 * @created_date: 06/04/20:10:05 AM
 * email:aag450@gmail.com, abdulraufgidado@yahoo.com
 **/
public interface XProjectConversionService {

    String create(@RequestBody ProjectDto projectDto) throws IOException;

    Map<String, Object> view(@PathVariable final String id);

    Map<String, Object> searchByProjectID(@PathVariable final String field);

    String update(@RequestBody ProjectDto projectDto) throws IOException;

    String delete(@PathVariable final String id);
}
