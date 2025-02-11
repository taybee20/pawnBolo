package pawnbolo.com.pawnbolo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pawnbolo.com.pawnbolo.models.PawnStore;

public interface PawnStoreRepository extends JpaRepository<PawnStore, Long> {
}
