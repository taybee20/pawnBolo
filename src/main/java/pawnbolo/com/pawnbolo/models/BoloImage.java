package pawnbolo.com.pawnbolo.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "BoloImages")
@Data
public class BoloImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "boloId", nullable = false)
    private Bolo bolo;

    @Column(nullable = false)
    private String imageUrl;

    private String caption;
}