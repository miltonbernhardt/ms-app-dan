package com.brikton.labapps.mspedidos.exception;

public class RecursoNoEncontradoException extends Exception{

    public RecursoNoEncontradoException(String string, Integer id) {
        super(string + Integer.toString(id));
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
}
