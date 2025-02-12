package pawnbolo.com.pawnbolo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pawnbolo.com.pawnbolo.models.PawnStore;
import pawnbolo.com.pawnbolo.models.PawnStoreRequest;
import pawnbolo.com.pawnbolo.models.User;
import pawnbolo.com.pawnbolo.repositories.PawnStoreRepository;
import pawnbolo.com.pawnbolo.repositories.UserRepository;
import pawnbolo.com.pawnbolo.services.S3Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pawn-stores")
public class PawnStoreController {
    private final PawnStoreRepository pawnStoreRepository;
    private final UserRepository userRepository;
    private final S3Service s3Service;

    public PawnStoreController(PawnStoreRepository pawnStoreRepository, UserRepository userRepository, S3Service s3Service) {
        this.pawnStoreRepository = pawnStoreRepository;
        this.userRepository = userRepository;
        this.s3Service = s3Service;
    }

    /**
     * Register a Pawn Store After User Signup
     */
    @PostMapping("/register-store/{userId}")
    public ResponseEntity<String> registerPawnStore(@PathVariable Long userId, @RequestBody PawnStoreRequest request) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found.");
        }
        User user = userOpt.get();

        if (user.getPawnStore() != null) {
            return ResponseEntity.badRequest().body("User already has a registered pawn store.");
        }

        // Create Pawn Store
        PawnStore pawnStore = new PawnStore();
        pawnStore.setBusinessName(request.getBusinessName());
        pawnStore.setLicenseNumber(request.getLicenseNumber());
        pawnStore.setLicenseExpiration(request.getLicenseExpiration());
        pawnStore.setApproved(false); // Default to false, pending admin approval
        pawnStoreRepository.save(pawnStore);

        // Link User to the Store
        user.setPawnStore(pawnStore);
        userRepository.save(user);

        return ResponseEntity.ok("Pawn store registered and linked to user. Awaiting approval.");
    }

    /**
     * Upload Business License File for a Pawn Store
     */
    @PostMapping("/{storeId}/upload-license")
    public ResponseEntity<String> uploadBusinessLicense(@PathVariable Long storeId, @RequestParam("file") MultipartFile file) {
        Optional<PawnStore> pawnStoreOpt = pawnStoreRepository.findById(storeId);
        if (pawnStoreOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Pawn store not found.");
        }

        PawnStore pawnStore = pawnStoreOpt.get();

        try {
            // Upload license to AWS S3
            String fileUrl = s3Service.uploadFile(file, pawnStore.getBusinessName());

            // Save the S3 file URL in the database
            pawnStore.setLicenseFileUrl(fileUrl);
            pawnStoreRepository.save(pawnStore);

            return ResponseEntity.ok("License uploaded successfully: " + fileUrl);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Error uploading file.");
        }
    }

    /**
     * Get Pawn Store Details by Store ID
     */
    @GetMapping("/get/{storeId}")
    public ResponseEntity<?> getPawnStore(@PathVariable Long storeId) {
        Optional<PawnStore> pawnStoreOpt = pawnStoreRepository.findById(storeId);
        if (pawnStoreOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Pawn store not found.");
        }
        return ResponseEntity.ok(pawnStoreOpt.get());
    }

    /**
     * Get All Pawn Stores
     */
    @GetMapping("/list")
    public ResponseEntity<List<PawnStore>> getAllPawnStores() {
        return ResponseEntity.ok(pawnStoreRepository.findAll());
    }

    /**
     * Delete a Pawn Store
     */
    @DeleteMapping("/delete/{storeId}")
    public ResponseEntity<String> deletePawnStore(@PathVariable Long storeId) {
        Optional<PawnStore> pawnStoreOpt = pawnStoreRepository.findById(storeId);
        if (pawnStoreOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Pawn store not found.");
        }

        pawnStoreRepository.deleteById(storeId);
        return ResponseEntity.ok("Pawn store deleted.");
    }
}
