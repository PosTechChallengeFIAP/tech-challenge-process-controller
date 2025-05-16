package com.tech.challenge.QueueService.core.application.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException (Class resourceClass){
        super(String.format("%s not found.", resourceClass.getSimpleName()));
    }

    public ResourceNotFoundException(Class resourceClass, String info) {
        super(String.format("%s not found. %s", resourceClass.getSimpleName(), info));
    }
}
