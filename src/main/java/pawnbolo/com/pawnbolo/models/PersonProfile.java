package pawnbolo.com.pawnbolo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "PersonProfiles")
@Data
public class PersonProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long personId;

    @Column(unique = true, nullable = false)
    private String govId;  // Government-issued ID

    private String firstName;
    private String lastName;

    @Temporal(TemporalType.DATE)
    private Date dob;

    private String knownAliases;
    private String address;
    private String photoUrl;

    @OneToMany(mappedBy = "personProfile", cascade = CascadeType.ALL)
    private List<Bolo> bolos;
}