package com.portops.auth_service.service;

import org.springframework.stereotype.Service;

import com.portops.auth_service.repository.I_UserAssignmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssignmentCleanupScheduler {

    private final I_UserAssignmentRepository repository;
    

    public void deactivateExpired() {
        repository.deactivateExpired();
    }

}
