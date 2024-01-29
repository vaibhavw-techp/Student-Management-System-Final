package com.project.StudentManagement.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception{
    public ResourceNotFoundException(Integer id){

        super("404 not found with id: "+id);
    }
}
