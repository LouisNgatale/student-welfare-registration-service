package com.louisngatale.registration_service.repositories;

import com.louisngatale.registration_service.entities.StudentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails,Integer> {
}
