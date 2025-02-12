package pawnbolo.com.pawnbolo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pawnbolo.com.pawnbolo.models.*;
import pawnbolo.com.pawnbolo.repositories.*;
import pawnbolo.com.pawnbolo.services.AuthService;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PawnStoreRepository pawnStoreRepository;

    public AdminController(UserRepository userRepository, AuthService authService, PawnStoreRepository pawnStoreRepository) {
        this.userRepository = userRepository;
        this.authService = authService;
        this.pawnStoreRepository = pawnStoreRepository;
    }
    @PostMapping("/approve/{userId}")
    public ResponseEntity<String> approveUser(@PathVariable Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found.");
        }
        User user = userOpt.get();

        if (user.getApprovalStatus() != ApprovalStatus.PENDING) {
            return ResponseEntity.badRequest().body("User is already processed.");
        }

        if (user.getPawnStore() == null) {
            return ResponseEntity.badRequest().body("User must be linked to a pawn store before approval.");
        }

        // Register user in Cognito without password (Cognito sends a password setup email)
        String cognitoUserId = authService.createCognitoUser(user.getEmail());
        user.setCognitoUserId(cognitoUserId);
        user.setApprovalStatus(ApprovalStatus.APPROVED);
        userRepository.save(user);

        return ResponseEntity.ok("User approved. Cognito password reset email sent.");
    }

    @PostMapping("/reject/{userId}")
    public ResponseEntity<String> rejectUser(@PathVariable Long userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found.");
        }
        User user = userOpt.get();

        if (!user.getApprovalStatus().equals(ApprovalStatus.PENDING)) {
            return ResponseEntity.badRequest().body("User is already processed.");
        }

        // Mark user as rejected
        user.setApprovalStatus(ApprovalStatus.REJECTED);
        userRepository.save(user);

        return ResponseEntity.ok("User rejected.");
    }

    /**
     * Admin Approves a Pawn Store
     */
    @PostMapping("/approve/{storeId}")
    public ResponseEntity<String> approvePawnStore(@PathVariable Long storeId) {
        Optional<PawnStore> pawnStoreOpt = pawnStoreRepository.findById(storeId);
        if (pawnStoreOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Pawn store not found.");
        }

        PawnStore pawnStore = pawnStoreOpt.get();
        if (pawnStore.isApproved()) {
            return ResponseEntity.badRequest().body("Pawn store is already approved.");
        }

        // Approve the store
        pawnStore.setApproved(true);
        pawnStoreRepository.save(pawnStore);

        return ResponseEntity.ok("Pawn store approved.");
    }
}
