package pawnbolo.com.pawnbolo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawnbolo.com.pawnbolo.models.Flag;

import java.util.List;

@Repository
public interface FlagRepository extends JpaRepository<Flag, Long> {

    // Find all flags associated with a specific BOLO by its ID
    List<Flag> findByBoloId(Long boloId);

    // Optionally, find all flags made by a specific user by their ID
    List<Flag> findByFlaggedBy_UserId(Long userId);
}
