package com.portops.auth_service.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;


@Entity
@Table(name = "user_assignment", schema = "auth")
public class UserAssignment {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserContext user;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type")
    private EntityType entityType; // SHIP, PORT, ORGANIZATION

    @Column(name = "entity_id")
    private UUID entityId;

    @Column(name = "role_in_context")
    private String roleInContext;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "status")
    private String status;
}
