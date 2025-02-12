package pawnbolo.com.pawnbolo.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pawn_stores")
@Getter
@Setter
public class PawnStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String businessName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false, length = 10)
    private String zip;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String licenseNumber;

    @Column(nullable = true)
    private String licenseUrl;

    @Column(nullable = false)
    private LocalDate licenseExpiration;

    @Column(nullable = false)
    private boolean isApproved = false;

    @OneToMany(mappedBy = "pawnStore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;

    @OneToMany(mappedBy = "pawnStore", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bolo> bolos;

    public void setApproved(boolean approved) {
        this.isApproved = approved;
    }

    public boolean isApproved() {
        return this.isApproved;
    }

    public void setLicenseFileUrl(String fileUrl) {
        this.licenseUrl = fileUrl;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLicenseExpiration() {
        return licenseExpiration;
    }

    public void setLicenseExpiration(LocalDate licenseExpiration) {
        this.licenseExpiration = licenseExpiration;
    }
}
