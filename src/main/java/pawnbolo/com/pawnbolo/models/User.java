package pawnbolo.com.pawnbolo.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String email;
    private String address;
    private String address2;
    private String city;
    private String state;
    private int zipcode;
    private String store_name;
    private String pawn_license;
    private String business_license;
    private boolean isAdmin;
    private boolean isVerified;
    private String phone;

    private String settings;
}