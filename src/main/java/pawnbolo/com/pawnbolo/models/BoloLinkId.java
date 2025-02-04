package pawnbolo.com.pawnbolo.models;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class BoloLinkId implements Serializable {
    private Long parentBoloId;
    private Long childBoloId;


}