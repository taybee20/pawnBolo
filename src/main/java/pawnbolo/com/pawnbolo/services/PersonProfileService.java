package pawnbolo.com.pawnbolo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawnbolo.com.pawnbolo.models.PersonProfile;
import pawnbolo.com.pawnbolo.repositories.PersonProfileRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonProfileService {
    private final PersonProfileRepository personProfileRepository;

    @Autowired
    public PersonProfileService(PersonProfileRepository personProfileRepository) {
        this.personProfileRepository = personProfileRepository;
    }

    public Optional<PersonProfile> getPersonProfileByGovId(String govId) {
        return personProfileRepository.findByGovId(govId);
    }

    public List<PersonProfile> getPersonProfilesByLastName(String lastName) {
        return personProfileRepository.findByLastName(lastName);
    }

    public List<PersonProfile> getPersonProfilesByFirstNameAndLastName(String firstName, String lastName) {
        return personProfileRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    public List<PersonProfile> getPersonProfilesByFirstName(String firstName) {
        return personProfileRepository.findByFirstName(firstName);
    }

    public List<PersonProfile> getPersonProfilesByAddress(String address) {
        return personProfileRepository.findByAddress(address);
    }

    public List<PersonProfile> getPersonProfilesByKnownAliases(String knownAliases) {
        return personProfileRepository.findByKnownAliases(knownAliases);
    }

    public List<PersonProfile> getPersonProfilesByFirstNameLastNameAndDob(String firstName, String lastName, Date dateOfBirth) {
        return personProfileRepository.findByFirstNameAndLastNameAndDob(firstName, lastName, dateOfBirth);
    }
}
