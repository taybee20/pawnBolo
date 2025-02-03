package pawnbolo.com.pawnbolo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "Flags")
@Data
public class Flag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flagId;

    @ManyToOne
    @JoinColumn(name = "boloId", nullable = false)
    private Bolo bolo;

    @ManyToOne
    @JoinColumn(name = "flaggedBy", nullable = false)
    private User flaggedBy;

    @Lob
    private String reason;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date = new Date();
}