package com.louisngatale.registration_service.controllers;

import com.louisngatale.registration_service.entities.AdminModel;
import com.louisngatale.registration_service.entities.ResponseModel;
import com.louisngatale.registration_service.entities.StudentModel;
import com.louisngatale.registration_service.exceptions.ApiRequestException;
import com.louisngatale.registration_service.services.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/registration")
public class ApiController {
    @Autowired
    private StudentServices studentServices;

    @RequestMapping(method = RequestMethod.POST,value = "/student")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createStudent(@RequestBody StudentModel model) {
//        TODO: Exception handling
            String jwt = studentServices.createStudent(model);
            return ResponseEntity.ok(new ResponseModel(jwt));
    }

    @RequestMapping(method = RequestMethod.POST,value = "/admin")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createAdmin(@RequestBody AdminModel model) {
//        TODO: Exception handling
            String jwt = studentServices.createAdmin(model);
            return ResponseEntity.ok(new ResponseModel(jwt));
    }
}
