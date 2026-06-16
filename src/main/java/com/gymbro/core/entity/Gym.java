package com.gymbro.core.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "gyms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 500)
    private String address;

    @Column(length = 20)
    private String phone;

    @Column(length = 255)
    private String email;

    @Column(name = "logo_url", length = 500)
    private String logoUrl;

    @Column(name = "branding_colors", length = 9)
    private String brandingColors;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Plan plan;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "gym")
    private List<Clients> clients;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum Plan {
        BASIC,
        PROFESSIONAL,
        ENTERPRISE
    }
}
