package com.uwindsor.comp8390.asynchronous.service.custom.impl;

import com.uwindsor.comp8390.asynchronous.dto.ProjectDto;
import com.uwindsor.comp8390.asynchronous.service.custom.XProjectConversionService;
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
 * @created_date: 06/04/20:9:51 AM
 * email:gidado@uwindsor.ca, abdulraufgidado@yahoo.com
 **/
@Slf4j
@Service
public class XProjectConversionServiceImpl implements XProjectConversionService {

    @Autowired
    Client client;


    /**
     private ZonedDateTime lastModified;
     private  String projName;
     private  Long projId;
     private  String location;
     private WorksOn worksOn;

     * @param projectDto
     * @return
     * @throws IOException
     */



    @PostMapping("/create")
    public String create(@RequestBody ProjectDto projectDto) throws IOException {
        IndexResponse response = client.prepareIndex(AppConstant.INDEX_NAME,
                AppConstant.DatabaseTableNames.PROJECT_TYPE, projectDto.getProjId().toString())
                .setSource(jsonBuilder()
                        .startObject()
                        .field(AppConstant.ProjectTableFieldName.PROJECT_ID, projectDto.getProjId())
                        .field(AppConstant.ProjectTableFieldName.PROJECT_NAME,projectDto.getProjName())
                        .field(AppConstant.ProjectTableFieldName.LOCATION, projectDto.getLocation())
                        .field(AppConstant.ProjectTableFieldName.WORKS_ON, projectDto.getWorksOn())
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
        GetResponse getResponse = client.prepareGet(AppConstant.INDEX_NAME, AppConstant.DatabaseTableNames.PROJECT_TYPE, id).get();
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
    public Map<String, Object> searchByProjectID(@PathVariable final String field) {
        Map<String,Object> map = null;
        SearchResponse response = client.prepareSearch(AppConstant.INDEX_NAME)
                .setTypes(AppConstant.DatabaseTableNames.PROJECT_TYPE)
                .setSearchType(SearchType.QUERY_AND_FETCH)
                .setQuery(QueryBuilders.matchQuery(AppConstant.ProjectTableFieldName.PROJECT_ID, field))
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
    public String update(@RequestBody ProjectDto projectDto) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index(AppConstant.INDEX_NAME)
                .type(AppConstant.DatabaseTableNames.PROJECT_TYPE)
                .id(projectDto.getProjId().toString())
                .doc(jsonBuilder()
                        .startObject()
                        .field(AppConstant.ProjectTableFieldName.PROJECT_ID, projectDto.getProjId())
                        .field(AppConstant.ProjectTableFieldName.PROJECT_NAME,projectDto.getProjName())
                        .field(AppConstant.ProjectTableFieldName.LOCATION, projectDto.getLocation())
                        .field(AppConstant.ProjectTableFieldName.WORKS_ON, projectDto.getWorksOn())
                        .endObject());
        try {
            UpdateResponse updateResponse = client.update(updateRequest).get();
            log.info("update request status for project on is {} ",updateResponse.status());
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
                AppConstant.DatabaseTableNames.PROJECT_TYPE, id).get();
        log.debug("delete response for project  with id {} is {}",id,deleteResponse.getResult().toString());
        return deleteResponse.getResult().toString();
    }
}
