package com.portops.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portops.auth_service.model.UserContext;

@Repository
public interface UserContextRepository extends JpaRepository<UserContext, String> {

    
} 
