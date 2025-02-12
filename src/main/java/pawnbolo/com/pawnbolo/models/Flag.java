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

    public Long getFlagId() {
        return flagId;
    }

    public void setFlagId(Long flagId) {
        this.flagId = flagId;
    }

    public Bolo getBolo() {
        return bolo;
    }

    public void setBolo(Bolo bolo) {
        this.bolo = bolo;
    }

    public User getFlaggedBy() {
        return flaggedBy;
    }

    public void setFlaggedBy(User flaggedBy) {
        this.flaggedBy = flaggedBy;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}