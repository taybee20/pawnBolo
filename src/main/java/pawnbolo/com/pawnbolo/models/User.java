package pawnbolo.com.pawnbolo.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Users")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "pawnStoreId", nullable = false)
    private PawnStore pawnStore;

    @Column(unique = true, nullable = false)
    private String email;

    // Reference to Cognito's user identifier
    @Column(unique = true, nullable = false)
    private String cognitoUserId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;  // Role can be ADMIN or EMPLOYEE

    private String name;

    @OneToMany(mappedBy = "createdBy", cascade = CascadeType.ALL)
    private List<Bolo> createdBolos;
}