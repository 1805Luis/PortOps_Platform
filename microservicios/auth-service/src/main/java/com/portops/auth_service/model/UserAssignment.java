package com.portops.auth_service.model;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "user_assignment", schema = "auth")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAssignment {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserContext user;

    @Enumerated(EnumType.STRING)
    @Column(name = "scope", nullable = false)
    private Scope scope;

    @Enumerated(EnumType.STRING)
    @Column(name = "entity_type")
    private EntityType entityType; // SHIP, PORT, ORGANIZATION

    @Column(name = "entity_id")
    private UUID entityId;

    @Column(name = "role_in_context", nullable = false)
    private String roleInContext;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;

    @Column(name = "status")
    private String status;
}
