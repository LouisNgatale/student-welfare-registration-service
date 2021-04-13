package com.louisngatale.registration_service.controllers;

import com.louisngatale.registration_service.entities.StudentModel;
import com.louisngatale.registration_service.entities.User;
import com.louisngatale.registration_service.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/registration")
public class ApiController {
    @Autowired
    private StudentServices studentServices;

    @RequestMapping(method = RequestMethod.POST,value = "/student")
    @ResponseStatus(HttpStatus.CREATED)
    public StudentModel createStudent(@RequestBody StudentModel model){
        studentServices.createStudent(model);
        return model;
    }
}
