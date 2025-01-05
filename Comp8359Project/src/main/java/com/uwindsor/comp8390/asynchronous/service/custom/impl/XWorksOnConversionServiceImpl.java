
package com.uwindsor.comp8390.asynchronous.service.custom.impl;

import com.uwindsor.comp8390.asynchronous.dto.WorksOnDto;
import com.uwindsor.comp8390.asynchronous.service.custom.XWorksOnConversionService;
import com.uwindsor.comp8390.asynchronous.util.AppConstant;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * @author: Gidado Abdulrauf Aremu
 * @created_date: 06/04/20:4:34 AM
 * email:gidado@uwindsor.ca, abdulraufgidado@yahoo.com
 **/

@Slf4j
@Service
public class XWorksOnConversionServiceImpl implements XWorksOnConversionService {


    @Autowired
    Client client;


    /**
     * @param worksOnDto
     * @return
     * @throws IOException
     */



@PostMapping("/create")
    public String create(@RequestBody WorksOnDto worksOnDto) throws IOException {

        String worksOnElasticId = worksOnDto.getEmployeeId()+"_"+worksOnDto.getDepartmentId()+"_"+worksOnDto.getProjectId();
        worksOnDto.setWorksElasticId(worksOnElasticId);
        IndexResponse response = client.prepareIndex(AppConstant.INDEX_NAME,
                AppConstant.DatabaseTableNames.WORKS_ON_TYPE, worksOnDto.getWorksElasticId())
                .setSource(jsonBuilder()
                        .startObject()
                        .field(AppConstant.WorksOnTableFieldName.WORKS_ON_ID, worksOnDto.getWorksElasticId())
                        .field(AppConstant.WorksOnTableFieldName.DEPT_ID,worksOnDto.getDepartmentId())
                        .field(AppConstant.WorksOnTableFieldName.DATE, worksOnDto.getDate())
                        .field(AppConstant.WorksOnTableFieldName.EMPL_ID, worksOnDto.getEmployeeId())
                        .field(AppConstant.WorksOnTableFieldName.PROJECT_ID, worksOnDto.getProjectId())
                        .field(AppConstant.WorksOnTableFieldName.WORK_HOUR, worksOnDto.getWorkHour())
                        .endObject()
                )
                .get();
        log.info("response id: {}", response.getId());
        return response.getResult().toString();
    }




/**
     * The method below search for a employee via the employee id
     * @param id
     * @return
     */


    @GetMapping("/view/{id}")
    public Map<String, Object> view(@PathVariable final String id) {
        GetResponse getResponse = client.prepareGet(AppConstant.INDEX_NAME, AppConstant.DatabaseTableNames.WORKS_ON_TYPE, id).get();
        return getResponse.getSource();
    }



/**
     * This is a search method, several methods are available for search
     *  For example, use rangeQuery() to search a field value in a particular range, like ages between 10 to 20 years.
     *  There is a  wildcardQuery() method to search a field with a wildcard. termQuery()is also available.
     * @param field
     * @return
     */


    @GetMapping("/view/name/{field}")
    public Map<String, Object> searchByEmployeeID(@PathVariable final String field) {
        Map<String,Object> map = null;
        SearchResponse response = client.prepareSearch(AppConstant.INDEX_NAME)
                .setTypes(AppConstant.DatabaseTableNames.WORKS_ON_TYPE)
                .setSearchType(SearchType.QUERY_AND_FETCH)
                .setQuery(QueryBuilders.matchQuery(AppConstant.EmployeeTableFieldsName.EMPL_ID, field))
                .get();

        List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
        map =   searchHits.get(0).getSource();
        return map;
    }




/**
     * Update the document by searching it with Id and replace the field value.
     * The client has a method called  update(). It accepts UpdateRequest as input which builds the update query.
     * @RequestBody EmployeeDto
     * @return
     * @throws IOException
     */



    @GetMapping("/update/")
    public String update(@RequestBody WorksOnDto worksOnDto) throws IOException {

        String worksOnElasticId = worksOnDto.getEmployeeId()+"_"+worksOnDto.getDepartmentId()+"_"+worksOnDto.getProjectId();
        worksOnDto.setWorksElasticId(worksOnElasticId);
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(AppConstant.INDEX_NAME)
                .type(AppConstant.DatabaseTableNames.WORKS_ON_TYPE)
                .id(worksOnDto.getWorksElasticId())
                .doc(jsonBuilder()
                        .startObject()
                        .field(AppConstant.WorksOnTableFieldName.WORKS_ON_ID, worksOnDto.getWorksElasticId())
                        .field(AppConstant.WorksOnTableFieldName.DEPT_ID,worksOnDto.getDepartmentId())
                        .field(AppConstant.WorksOnTableFieldName.DATE, worksOnDto.getDate())
                        .field(AppConstant.WorksOnTableFieldName.EMPL_ID, worksOnDto.getEmployeeId())
                        .field(AppConstant.WorksOnTableFieldName.PROJECT_ID, worksOnDto.getProjectId())
                        .field(AppConstant.WorksOnTableFieldName.WORK_HOUR, worksOnDto.getWorkHour())
                        .endObject());
        try {
            UpdateResponse updateResponse = client.update(updateRequest).get();
            log.info("update request status for Works on is {} ",updateResponse.status());
            return updateResponse.status().toString();
        } catch (InterruptedException | ExecutionException e) {
            log.error("exception details is {} ",e);
        }
        return "Exception";
    }




/**
     *     The client does have a prepareDelete() method which accepts the index, type, and id to delete the document.
     */



    @GetMapping("/delete/{id}")
    public String delete(@PathVariable final String id) {
        DeleteResponse deleteResponse = client.prepareDelete(AppConstant.INDEX_NAME,
                AppConstant.DatabaseTableNames.WORKS_ON_TYPE, id).get();
        log.debug("delete response for works on  with id {} is {}",id,deleteResponse.getResult().toString());
        return deleteResponse.getResult().toString();
    }
}

