package com.portops.auth_service.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.portops.auth_service.service.I_SchedulerService;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AssignmentCleanupScheduler {

    private final EntityManager entityManager;
    private final I_SchedulerService service;


    private static final long LOCK_ID =  "auth.cleanup.assignments".hashCode();

    @Scheduled(cron = "0 0 3 * * *")
    public void deactivateExpiredAssignments() {

        boolean locked = entityManager
            .createNativeQuery("SELECT pg_try_advisory_lock(:id)")
            .setParameter("id", LOCK_ID)
            .getSingleResult()
            .equals(true);

        if (!locked) return;

        try {
            service.deactivateExpired();
        } finally {
            entityManager
                .createNativeQuery("SELECT pg_advisory_unlock(:id)")
                .setParameter("id", LOCK_ID)
                .executeUpdate();
        }
    }

}
