package pawnbolo.com.pawnbolo.models;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class PawnStoreRequest {
    private String businessName;
    private String licenseNumber;
    private LocalDate licenseExpiration;
}