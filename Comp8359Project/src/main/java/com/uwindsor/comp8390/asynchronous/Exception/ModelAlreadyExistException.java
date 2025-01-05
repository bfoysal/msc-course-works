package com.uwindsor.comp8390.asynchronous.Exception;

/**
 * @author: Gidado Abdulrauf Aremu
 * @created_date: 03/04/20:11:07 PM
 * email:gidado@uwindsor.ca, abdulraufgidado@yahoo.com
 **/
public class ModelAlreadyExistException extends Exception{
    private String customResponse;

    public ModelAlreadyExistException(String message) {
        super(message);
        this.customResponse=message;
    }
}
