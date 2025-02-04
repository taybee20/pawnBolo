package pawnbolo.com.pawnbolo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "BoloLinks")
@Data
public class BoloLink {
    @EmbeddedId
    private BoloLinkId id;

    @ManyToOne
    @MapsId("parentBoloId")
    @JoinColumn(name = "parentBoloId")
    private Bolo parentBolo;

    @ManyToOne
    @MapsId("childBoloId")
    @JoinColumn(name = "childBoloId")
    private Bolo childBolo;

    private String linkType;
}