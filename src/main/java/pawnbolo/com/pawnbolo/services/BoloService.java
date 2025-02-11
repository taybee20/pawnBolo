package pawnbolo.com.pawnbolo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawnbolo.com.pawnbolo.models.Bolo;
import pawnbolo.com.pawnbolo.models.BoloType;
import pawnbolo.com.pawnbolo.models.Flag;
import pawnbolo.com.pawnbolo.repositories.BoloRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BoloService {

    private final BoloRepository boloRepository;

    @Autowired
    public BoloService(BoloRepository boloRepository) {
        this.boloRepository = boloRepository;
    }


    /**
     * Retrieve a BOLO by its ID.
     * @param boloId the ID of the BOLO.
     * @return an Optional containing the found BOLO, or empty if not found.
     */
    public Optional<Bolo> getBoloById(Long boloId) {
        return boloRepository.findByBoloId(boloId);
    }

    /**
     * Retrieve BOLOs by their type.
     * @param boloType the type of the BOLO (e.g., ITEM or PERSON).
     * @return a list of BOLOs that match the specified type.
     */
    public List<Bolo> getBolosByType(BoloType boloType) {
        return boloRepository.findByBoloType(boloType);
    }

    /**
     * Create a new BOLO record.
     * @param bolo the BOLO object to create.
     * @return the saved BOLO.
     */
    public Bolo createBolo(Bolo bolo) {
        return boloRepository.save(bolo);
    }

    /**
     * Retrieve a BOLO by an associated item's serial number.
     * @param serialNumber the serial number to search for.
     * @return an Optional containing the found BOLO, or empty if not found.
     */
    public Optional<Bolo> getBoloBySerialNumber(String serialNumber) {
        return boloRepository.findBoloBySerialNumber(serialNumber);
    }

    /**
     * Retrieve BOLOs associated with a specific model.
     * @param model the model to search for.
     * @return a list of BOLOs that have items with the specified model.
     */
    public List<Bolo> getBolosByModel(String model) {
        return boloRepository.findByModel(model);
    }

    /**
     * Retrieve person BOLOs by first name and last name.
     * @param firstName the first name to search for.
     * @param lastName the last name to search for.
     * @return a list of BOLOs with associated person profiles that match the given name.
     */
    public List<Bolo> getPersonBolosByName(String firstName, String lastName) {
        return boloRepository.findPersonBolosByName(firstName, lastName);
    }

    /**
     * Retrieve person BOLOs by first name, last name, and date of birth.
     * @param firstName the first name to search for.
     * @param lastName the last name to search for.
     * @param dob the date of birth to search for.
     * @return a list of BOLOs with associated person profiles that match the criteria.
     */
    public List<Bolo> getPersonBolosByNameAndDob(String firstName, String lastName, java.util.Date dob) {
        return boloRepository.findPersonBolosByNameAndDob(firstName, lastName, dob);
    }

    public Bolo createPersonBolo(Bolo bolo) {
        bolo.setBoloType(BoloType.PERSON);
        return boloRepository.save(bolo);
    }
    public Bolo createItemBolo(Bolo bolo) {
        bolo.setBoloType(BoloType.ITEM);
        return boloRepository.save(bolo);
    }
    public void deleteBolo(Long boloId) {
        boloRepository.deleteById(boloId);
    }

    // Flag a BOLO
    //TODO:
    public Flag flagBolo(Long boloId, Flag flag) {
        Bolo bolo = boloRepository.findById(boloId)
                .orElseThrow(() -> new RuntimeException("BOLO not found")); // or a custom exception

        flag.setBolo(bolo);
        // If you track who flagged it, set flag.setFlaggedBy(someUser) here
        return flagRepository.save(flag);
    }

}
