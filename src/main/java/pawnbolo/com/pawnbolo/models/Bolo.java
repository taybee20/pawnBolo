package pawnbolo.com.pawnbolo.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
public class Bolo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boloId;

    //private User reportedBy;

    private int status;

    private int zipcode;

    private boolean isFlagged;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
