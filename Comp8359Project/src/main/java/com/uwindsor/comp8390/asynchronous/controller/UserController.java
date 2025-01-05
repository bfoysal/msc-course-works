/*
package com.uwindsor.comp8390.asynchronous.controller;

import com.uwindsor.comp8390.asynchronous.domain.User;
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
import org.springframework.stereotype.Controller;
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

*/
/**
 * @author: Gidado Abdulrauf Aremu
 * @created_date: 29/03/20:11:33 AM
 * email:gidado@uwindsor.ca, abdulraufgidado@yahoo.com
 **//*

@Controller
@Slf4j
public class UserController {



    @Autowired
    Client client;

    @PostMapping("/create")
    public String create(@RequestBody User user) throws IOException {
        IndexResponse response = client.prepareIndex("users", "employee", user.getUserId())
                .setSource(jsonBuilder()
                        .startObject()
                        .field("name", user.getName())
                        .field("userSettings", user.getUserSettings())
                        .endObject()
                )
                .get();
        log.info("response id: {}", response.getId());
        return response.getResult().toString();
    }


    @GetMapping("/view/{id}")
    public Map<String, Object> view(@PathVariable final String id) {
        GetResponse getResponse = client.prepareGet("users", "employee", id).get();
        return getResponse.getSource();
    }

    */
/**
     * This is a search method, several methods are available for search
     *  For example, use rangeQuery() to search a field value in a particular range, like ages between 10 to 20 years.
     *  There is a  wildcardQuery() method to search a field with a wildcard. termQuery()is also available.
     * @param field
     * @return
     *//*

    @GetMapping("/view/name/{field}")
    public Map<String, Object> searchByName(@PathVariable final String field) {
        Map<String,Object> map = null;
        SearchResponse response = client.prepareSearch("users")
                .setTypes("employee")
                .setSearchType(SearchType.QUERY_AND_FETCH)
                .setQuery(QueryBuilders.matchQuery("name", field))
                                .get();

        List<SearchHit> searchHits = Arrays.asList(response.getHits().getHits());
        map =   searchHits.get(0).getSourceAsMap();
        return map;
    }


    */
/**
     * Update the document by searching it with Id and replace the field value.
     * The client has a method called  update(). It accepts UpdateRequest as input which builds the update query.
     * @param id
     * @return
     * @throws IOException
     *//*


    @GetMapping("/update/{id}")
    public String update(@PathVariable final String id) throws IOException {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("users")
                .type("employee")
                .id(id)
                .doc(jsonBuilder()
                        .startObject()
                        .field("name", "Rajesh")
                        .endObject());
        try {
            UpdateResponse updateResponse = client.update(updateRequest).get();
            System.out.println(updateResponse.status());
            return updateResponse.status().toString();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
        return "Exception";
    }


*/
/**
 *     The client does have a prepareDelete() method which accepts the index, type, and id to delete the document.
 *//*


@GetMapping("/delete/{id}")
public String delete(@PathVariable final String id) {
    DeleteResponse deleteResponse = client.prepareDelete("users", "employee", id).get();
    return deleteResponse.getResult().toString();
}


}
*/
