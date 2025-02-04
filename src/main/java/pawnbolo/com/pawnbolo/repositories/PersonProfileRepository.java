package pawnbolo.com.pawnbolo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pawnbolo.com.pawnbolo.models.PersonProfile;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PersonProfileRepository extends JpaRepository <PersonProfile, Long> {

    /**
     * Finds a PersonProfile by its government-issued ID.
     * @param govId the government-issued ID.
     * @return an Optional containing the found PersonProfile, or empty if not found.
     */
    Optional<PersonProfile> findByGovId (String govId);

    /**
     * Retrieves a list of PersonProfiles by the specified last name.
     * @param lastName the last name of the person.
     * @return a list of PersonProfiles matching the given last name.
     */
    List<PersonProfile> findByLastName (String lastName);

    /**
     * Retrieves a list of PersonProfiles by the specified first and last name.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @return a list of PersonProfiles matching the given first and last name.
     */
    List<PersonProfile> findByFirstNameAndLastName (String firstName, String lastName);

    /**
     * Retrieves a list of PersonProfiles by the specified first name.
     * @param firstName the first name of the person.
     * @return a list of PersonProfiles matching the given first name.
     */
    List<PersonProfile> findByFirstName (String firstName);

    /**
     * Retrieves a list of PersonProfiles by the specified address.
     * @param address the address of the person.
     * @return a list of PersonProfiles matching the given address.
     */
    List<PersonProfile> findByAddress (String address);

    /**
     * Retrieves a list of PersonProfiles by the specified known aliases.
     * @param knownAliases the known aliases of the person.
     * @return a list of PersonProfiles matching the given known aliases.
     */
    List<PersonProfile> findByKnownAliases (String knownAliases);

    /**
     * Retrieves a list of PersonProfiles by the specified first name, last name, and date of birth.
     * @param firstName the first name of the person.
     * @param lastName the last name of the person.
     * @param dob the date of birth of the person.
     * @return a list of PersonProfiles matching the given first name, last name, and date of birth.
     */
    List<PersonProfile> findByFirstNameAndLastNameAndDob(String firstName, String lastName, Date dob);


}
