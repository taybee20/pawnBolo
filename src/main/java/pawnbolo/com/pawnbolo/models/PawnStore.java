package pawnbolo.com.pawnbolo.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "PawnStores")
@Data
public class PawnStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pawnStoreId;

    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;
    private String email;

    @OneToMany(mappedBy = "pawnStore", cascade = CascadeType.ALL)
    private List<User> users;

    @OneToMany(mappedBy = "pawnStore", cascade = CascadeType.ALL)
    private List<Bolo> bolos;
}