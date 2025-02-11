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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PawnStore getPawnStore() {
        return pawnStore;
    }

    public void setPawnStore(PawnStore pawnStore) {
        this.pawnStore = pawnStore;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCognitoUserId() {
        return cognitoUserId;
    }

    public void setCognitoUserId(String cognitoUserId) {
        this.cognitoUserId = cognitoUserId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Bolo> getCreatedBolos() {
        return createdBolos;
    }

    public void setCreatedBolos(List<Bolo> createdBolos) {
        this.createdBolos = createdBolos;
    }
}