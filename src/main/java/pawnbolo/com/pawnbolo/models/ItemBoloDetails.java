package pawnbolo.com.pawnbolo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ItemBoloDetails")
@Data
public class ItemBoloDetails {
    @Id
    private Long boloId;  // Same as the Bolo's primary key

    @OneToOne
    @MapsId
    @JoinColumn(name = "boloId")
    private Bolo bolo;

    private String serialNumber;
    private String category;
    private String brand;
    private String model;
    private String color;
}
