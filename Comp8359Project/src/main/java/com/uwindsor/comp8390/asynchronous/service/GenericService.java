/*
package com.uwindsor.comp8390.asynchronous.service;

import com.uwindsor.comp8390.asynchronous.Exception.ModelAlreadyExistException;
import com.uwindsor.comp8390.asynchronous.Exception.ModelNotFoundException;

import java.io.Serializable;
import java.util.List;

*/
/**
 * @author: Gidado Abdulrauf Aremu
 * @created_date: 06/04/20:1:10 AM
 * email:gidado@uwindsor.ca, abdulraufgidado@yahoo.com
 **//*

    public interface GenericService<T, Long extends Serializable> {

        */
/**
         * @param id
         * @return
         * @throws ModelNotFoundException
         *//*

        T findById(Long id) throws ModelNotFoundException;

        */
/**
         * @return
         *//*

        List<T> findAll();

        */
/**
         * @param id
         * @return
         * @throws ModelNotFoundException
         *//*

        T deleteById(Long id) throws ModelNotFoundException;

        */
/**
         * @param entity
         * @return
         *//*

        T save(T entity) throws ModelAlreadyExistException, ModelNotFoundException;

        */
/**
         * @param updated
         * @return
         * @throws ModelNotFoundException
         *//*

        T update(T updated) throws ModelNotFoundException;

    }

*/
