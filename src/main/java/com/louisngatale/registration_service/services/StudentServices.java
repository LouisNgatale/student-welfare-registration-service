package com.louisngatale.registration_service.services;

import com.louisngatale.registration_service.entities.Roles;
import com.louisngatale.registration_service.entities.StudentDetails;
import com.louisngatale.registration_service.entities.StudentModel;
import com.louisngatale.registration_service.entities.User;
import com.louisngatale.registration_service.repositories.RolesRepository;
import com.louisngatale.registration_service.repositories.StudentDetailsRepository;
import com.louisngatale.registration_service.repositories.UserRepository;
import com.louisngatale.registration_service.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServices {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RolesRepository rolesRepository;

//    TODO: User roles enum
    private List<Roles> roles;

    @Autowired
    private StudentDetailsRepository detailsRepository;

    public StudentModel createStudent(StudentModel model){
        boolean userExists = userRepository
                .findByloginId(model.getRegistrationNumber())
                .isPresent();


        if (userExists){
            throw new IllegalStateException("Registration number already taken");
        }else {

//        Initialize roles list object
            roles = new ArrayList<>();

//        Create array of student roles
            roles.add(rolesRepository.findByRole("STUDENT"));

            String encodedPassword = passwordEncoder.encode(model.getPassword());

//        Create new user (Student) object for saving
            User newStudent = new User(model.getFullName(),
                    model.getGender(),
                    model.getRegistrationNumber(),
                    encodedPassword,
                    roles);

//        Save student object and get the id
            User studentObj = userRepository.saveAndFlush(newStudent);

//        Create instance of new student details to link to the user
            StudentDetails studentDetails = new StudentDetails();
            studentDetails.setCourse(model.getCourse());
            studentDetails.setLevel(model.getLevel());
            studentDetails.setYearOfStudy(model.getYearOfStudy());
            studentDetails.setStudentId(studentObj);
            studentDetails.setPhoneNumber(model.getPhoneNumber());

//            Save student details
            detailsRepository.save(studentDetails);

//        detailsRepository.save();
            return model;
        }

    }
}
