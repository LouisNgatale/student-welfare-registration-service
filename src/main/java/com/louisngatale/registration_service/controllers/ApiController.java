package com.louisngatale.registration_service.controllers;

import com.louisngatale.registration_service.entities.StudentModel;
import com.louisngatale.registration_service.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/registration")
public class ApiController {
    @Autowired
    private StudentServices studentServices;

    @RequestMapping(method = RequestMethod.POST,value = "/student")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentModel createStudent(@RequestBody StudentModel model){
//        TODO: Exception handling
        studentServices.createStudent(model);
        return model;
    }
}
