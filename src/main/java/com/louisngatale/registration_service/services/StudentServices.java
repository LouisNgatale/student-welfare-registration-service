package com.louisngatale.registration_service.services;

import com.louisngatale.registration_service.entities.*;
import com.louisngatale.registration_service.exceptions.ApiRequestException;
import com.louisngatale.registration_service.repositories.RolesRepository;
import com.louisngatale.registration_service.repositories.StudentDetailsRepository;
import com.louisngatale.registration_service.repositories.UserRepository;
import com.louisngatale.registration_service.security.JwtUtil;
import com.louisngatale.registration_service.security.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

//    TODO: User roles enum
    private List<Roles> roles;

    @Autowired
    private StudentDetailsRepository detailsRepository;

    @Transactional
    public String createStudent(StudentModel model){
        String registrationNumber = model.getRegistrationNumber();
        boolean userExists = userRepository
                .findByloginId(registrationNumber)
                .isPresent();

        if (!userExists) {
//        Initialize roles list object
            roles = new ArrayList<>();

//        Create array of student roles
            roles.add(rolesRepository.findByRole("STUDENT"));

            String encodedPassword = passwordEncoder.encode(model.getPassword());

//        Create new user (Student) object for saving
            User newStudent = new User(model.getFullName(), model.getGender(), registrationNumber, encodedPassword, roles);

//        Save student object and get the id
            User studentObj = userRepository.save(newStudent);
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
            return generateToken(registrationNumber);
        } else {
            throw new ApiRequestException("User already exists");
        }

    }

//    Generate json token for the user after registration
    public String generateToken(String username){
        try {
//            Generate jwt
            final String jwt = jwtUtil.generateToken(username);

//            Return jwt
            return jwt;
        }catch (Exception e){
            throw new ApiRequestException("Couldn't generate token for the user!", e);
        }
    }

    public String createAdmin(AdminModel model) {
        String loginId = model.getLoginId();
        boolean userExists = userRepository
                .findByloginId(loginId)
                .isPresent();

        if (!userExists) {
//        Initialize roles list object
            roles = new ArrayList<>();

//        Create array of student roles
            roles.add(rolesRepository.findByRole("ADMIN"));
            roles.add(rolesRepository.findByRole("REGISTRAR"));
            roles.add(rolesRepository.findByRole("HOD"));
            roles.add(rolesRepository.findByRole("DEAN"));

            String encodedPassword = passwordEncoder.encode(model.getPassword());

//        Create new user (Student) object for saving
            User newStudent = new User(model.getFullName(), model.getGender(), loginId, encodedPassword, roles);
            userRepository.save(newStudent);
            return generateToken(loginId);
        } else {
            throw new ApiRequestException("User already exists");
        }
    }
}
