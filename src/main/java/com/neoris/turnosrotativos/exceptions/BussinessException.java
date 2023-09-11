package com.neoris.turnosrotativos.exceptions;


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
