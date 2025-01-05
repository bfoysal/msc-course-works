package com.uwindsor.comp8390.asynchronous.Exception;

import lombok.Data;

/**
 *
 * @author abdulrauf.gidado
 */
@Data
public class ModelNotFoundException extends Exception {

    private String customResponse;

    public ModelNotFoundException(String message) {
        super(message);
        this.customResponse=message;
    }
}
