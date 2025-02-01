package pawnbolo.com.pawnbolo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawnbolo.com.pawnbolo.models.Bolo;

@Repository
public interface BoloRepository extends JpaRepository <Bolo, Long> {

}
