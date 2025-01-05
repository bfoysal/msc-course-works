package com.uwindsor.comp8390.asynchronous.domain;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Gidado Abdulrauf Aremu
 * @created_date: 29/03/20:11:08 AM
 * email:aag450@gmail.com, abdulraufgidado@yahoo.com
 **/
@Slf4j
@Data
public class User {

        private String userId;
        private String name;
        private Date creationDate = new Date();
        private Map<String, String> userSettings = new HashMap<>();

    }

