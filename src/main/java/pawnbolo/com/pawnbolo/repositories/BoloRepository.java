package pawnbolo.com.pawnbolo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pawnbolo.com.pawnbolo.models.Bolo;
import pawnbolo.com.pawnbolo.models.BoloType;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface BoloRepository extends JpaRepository<Bolo, Long> {

    /**
     * Finds a Bolo by its primary key.
     * @param boloId the ID of the Bolo.
     * @return an Optional containing the found Bolo, or empty if not found.
     */
    Optional<Bolo> findByBoloId(Long boloId);

    /**
     * Retrieves a list of BOLOs by the specified BoloType.
     * @param boloType the type of the BOLO (e.g., ITEM or PERSON).
     * @return a list of BOLOs matching the given type.
     */
    List<Bolo> findByBoloType(BoloType boloType);

    /**
     * Retrieves a list of BOLOs for a given PawnStore based on the pawn store's ID.

     * @param pawnStoreId the ID of the PawnStore.
     * @return a list of BOLOs associated with the specified PawnStore.
     */
    @Query("SELECT b FROM Bolo b WHERE b.pawnStore.pawnStoreId = :pawnStoreId")
    List<Bolo> findByPawnStoreId(@Param("pawnStoreId") Long pawnStoreId);

    /**
     * Retrieves BOLOs created by a specific user.
     *
     * @param userId the ID of the user who created the BOLO.
     * @return a list of BOLOs created by the given user.
     */
    @Query("SELECT b FROM Bolo b WHERE b.createdBy.userId = :userId")
    List<Bolo> findByCreatedByUserId(@Param("userId") Long userId);

    @Query("SELECT b FROM Bolo b JOIN b.itemBoloDetails d WHERE d.serialNumber = :serialNumber")
    Optional<Bolo> findBoloBySerialNumber(@Param("serialNumber") String serialNumber);

    @Query("SELECT b FROM Bolo b JOIN b.itemBoloDetails d WHERE d.model = :model")
    List<Bolo> findByModel(@Param("model") String model);

    /**
     * Retrieves a list of person BOLOs by first name and last name.
     * Only BOLOs with a boloType of PERSON and an associated PersonProfile are returned.
     * @param firstName the first name to search for.
     * @param lastName the last name to search for.
     * @return a list of BOLOs that match the criteria.
     */
    @Query("SELECT b FROM Bolo b JOIN b.personProfile p " +
            "WHERE b.boloType = 'PERSON' " +
            "AND p.firstName = :firstName " +
            "AND p.lastName = :lastName")
    List<Bolo> findPersonBolosByName(@Param("firstName") String firstName,
                                     @Param("lastName") String lastName);

    /**
     * Retrieves a list of person BOLOs by first name, last name, and date of birth.
     * Only BOLOs with a boloType of PERSON and an associated PersonProfile are returned.
     * @param firstName the first name to search for.
     * @param lastName the last name to search for.
     * @param dob the date of birth to search for.
     * @return a list of BOLOs that match the criteria.
     */
    @Query("SELECT b FROM Bolo b JOIN b.personProfile p " +
            "WHERE b.boloType = 'PERSON' " +
            "AND p.firstName = :firstName " +
            "AND p.lastName = :lastName " +
            "AND p.dob = :dob")
    List<Bolo> findPersonBolosByNameAndDob(@Param("firstName") String firstName,
                                           @Param("lastName") String lastName,
                                           @Param("dob") Date dob);
}
