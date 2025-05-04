package com.example.demo.exceptionHandler;

public class ExceptionHandler extends  RuntimeException{

    public ExceptionHandler (String message){
        super(message);
    }

    public ExceptionHandler(String message,Throwable throwable){
        super(message,throwable);
    }
}
