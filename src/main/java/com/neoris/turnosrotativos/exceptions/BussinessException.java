package com.neoris.turnosrotativos.exceptions;

import org.springframework.data.crossstore.ChangeSetPersister;

public class BussinessException extends RuntimeException{

    private Integer status;

    public BussinessException(String message, Integer status) {

        super(message);
        this.status = status;


    }

    public Integer getStatus() {
        return status;
    }
}
