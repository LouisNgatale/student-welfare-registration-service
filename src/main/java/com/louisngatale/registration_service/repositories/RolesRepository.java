package com.louisngatale.registration_service.repositories;

import com.louisngatale.registration_service.entities.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Roles,Long> {
    Roles findByRole(String role);
}
