package pawnbolo.com.pawnbolo.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "BOLOs")
@Data
public class Bolo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boloId;

    @ManyToOne
    @JoinColumn(name = "pawnStoreId", nullable = false)
    private PawnStore pawnStore;

    @ManyToOne
    @JoinColumn(name = "createdBy", nullable = false)
    private User createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private BoloType boloType; // ITEM or PERSON

    // For person BOLOs, link to a PersonProfile (nullable for item BOLOs)
    @ManyToOne
    @JoinColumn(name = "personId")
    private PersonProfile personProfile;

    @Lob
    private String notes;

    // Store additional metadata as a JSON string (consider a converter if needed)
    @Column(columnDefinition = "json")
    private String metadata;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FlagStatus flagStatus = FlagStatus.NONE;

    // One-to-One: Only applicable if the BOLO is for an item
    @OneToOne(mappedBy = "bolo", cascade = CascadeType.ALL)
    private ItemBoloDetails itemBoloDetails;

    // One-to-Many: BOLO can have many images
    @OneToMany(mappedBy = "bolo", cascade = CascadeType.ALL)
    private List<BoloImage> boloImages;

    // One-to-Many: BOLO can have multiple flags
    @OneToMany(mappedBy = "bolo", cascade = CascadeType.ALL)
    private List<Flag> flags;
}
