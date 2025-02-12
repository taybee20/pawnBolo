package pawnbolo.com.pawnbolo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pawnbolo.com.pawnbolo.models.Bolo;
import pawnbolo.com.pawnbolo.models.Flag;
import pawnbolo.com.pawnbolo.models.User;
import pawnbolo.com.pawnbolo.repositories.BoloRepository;
import pawnbolo.com.pawnbolo.repositories.FlagRepository;
import pawnbolo.com.pawnbolo.repositories.UserRepository;

import java.util.List;

@Service
public class FlagService {

    private final FlagRepository flagRepository;
    private final BoloRepository boloRepository;
    private final UserRepository userRepository;

    @Autowired
    public FlagService(FlagRepository flagRepository,
                       BoloRepository boloRepository,
                       UserRepository userRepository) {
        this.flagRepository = flagRepository;
        this.boloRepository = boloRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create a flag for a BOLO.
     * @param boloId The ID of the BOLO being flagged.
     * @param userId The ID of the user flagging the BOLO.
     * @param reason The reason for flagging.
     * @return The newly created Flag.
     */
    public Flag createFlag(Long boloId, Long userId, String reason) {
        // Retrieve the BOLO to be flagged
        Bolo bolo = boloRepository.findById(boloId)
                .orElseThrow(() -> new RuntimeException("BOLO not found"));

        // Retrieve the user who flagged it
        User flaggedBy = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Create and set up the flag
        Flag flag = new Flag();
        flag.setBolo(bolo);
        flag.setFlaggedBy(flaggedBy);
        flag.setReason(reason);
        // The date is automatically set by the Flag entity's default

        return flagRepository.save(flag);
    }

    /**
     * Retrieve all flags (for admin review).
     * @return List of all Flag records.
     */
    public List<Flag> getAllFlags() {
        return flagRepository.findAll();
    }

    /**
     * Retrieve flags associated with a given BOLO.
     * @param boloId The ID of the BOLO.
     * @return List of Flag records for that BOLO.
     */
    public List<Flag> getFlagsByBoloId(Long boloId) {
        return flagRepository.findByBolo_BoloId(boloId);
    }

    /**
     * Retrieve flags created by a specific user.
     * @param userId The ID of the user.
     * @return List of Flag records made by that user.
     */
    public List<Flag> getFlagsByUserId(Long userId) {
        return flagRepository.findByFlaggedBy_UserId(userId);
    }
}
