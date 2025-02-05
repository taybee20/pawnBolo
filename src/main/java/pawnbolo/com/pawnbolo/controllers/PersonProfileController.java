package pawnbolo.com.pawnbolo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pawnbolo.com.pawnbolo.models.PersonProfile;
import pawnbolo.com.pawnbolo.services.PersonProfileService;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personProfiles")
public class PersonProfileController {

    private final PersonProfileService personProfileService;

    @Autowired
    public PersonProfileController(PersonProfileService personProfileService) {
        this.personProfileService = personProfileService;
    }

    /**
     * Retrieve a person profile by government-issued ID.
     * Example: GET /api/personProfiles/gov/ABC123
     */
    @GetMapping("/gov/{govId}")
    public ResponseEntity<Optional<PersonProfile>> getPersonProfileByGovId(@PathVariable String govId) {
        return ResponseEntity.ok(personProfileService.getPersonProfileByGovId(govId));
    }

    /**
     * Retrieve person profiles by last name.
     * Example: GET /api/personProfiles/lastname/Smith
     */
    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<PersonProfile>> getPersonProfilesByLastName(@PathVariable String lastName) {
        return ResponseEntity.ok(personProfileService.getPersonProfilesByLastName(lastName));
    }

    /**
     * Retrieve person profiles by first name and last name.
     * Example: GET /api/personProfiles/name?firstName=John&lastName=Doe
     */
    @GetMapping("/name")
    public ResponseEntity<List<PersonProfile>> getPersonProfilesByName(
            @RequestParam String firstName,
            @RequestParam String lastName) {
        return ResponseEntity.ok(personProfileService.getPersonProfilesByFirstNameAndLastName(firstName, lastName));
    }

    /**
     * Retrieve person profiles by first name.
     * Example: GET /api/personProfiles/firstname?firstName=John
     */
    @GetMapping("/firstname")
    public ResponseEntity<List<PersonProfile>> getPersonProfilesByFirstName(@RequestParam String firstName) {
        return ResponseEntity.ok(personProfileService.getPersonProfilesByFirstName(firstName));
    }

    /**
     * Retrieve person profiles by address.
     * Example: GET /api/personProfiles/address?address=123%20Main%20St
     */
    @GetMapping("/address")
    public ResponseEntity<List<PersonProfile>> getPersonProfilesByAddress(@RequestParam String address) {
        return ResponseEntity.ok(personProfileService.getPersonProfilesByAddress(address));
    }

    /**
     * Retrieve person profiles by known aliases.
     * Example: GET /api/personProfiles/aliases?knownAliases=JDoe
     */
    @GetMapping("/aliases")
    public ResponseEntity<List<PersonProfile>> getPersonProfilesByKnownAliases(@RequestParam String knownAliases) {
        return ResponseEntity.ok(personProfileService.getPersonProfilesByKnownAliases(knownAliases));
    }

    /**
     * Retrieve person profiles by first name, last name, and date of birth.
     * Example: GET /api/personProfiles/search?firstName=John&lastName=Doe&dob=1990-01-01
     *
     * Note: Ensure that the date format sent by the client can be parsed into a java.util.Date.
     */
    @GetMapping("/search")
    public ResponseEntity<List<PersonProfile>> getPersonProfilesByNameAndDob(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam("dob") Date dateOfBirth) {
        return ResponseEntity.ok(personProfileService.getPersonProfilesByFirstNameLastNameAndDob(firstName, lastName, dateOfBirth));
    }
}
