package pawnbolo.com.pawnbolo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pawnbolo.com.pawnbolo.models.Flag;
import pawnbolo.com.pawnbolo.services.FlagService;

import java.util.List;

@RestController
@RequestMapping("/api/flags")
public class FlagController {

    private final FlagService flagService;

    @Autowired
    public FlagController(FlagService flagService) {
        this.flagService = flagService;
    }

    /**
     * Endpoint to create a new flag for a BOLO.
     * Example request: POST /api/flags?boloId=1&userId=2&reason=Incorrect+information
     */
    @PostMapping
    public ResponseEntity<Flag> createFlag(@RequestParam Long boloId,
                                           @RequestParam Long userId,
                                           @RequestParam String reason) {
        Flag createdFlag = flagService.createFlag(boloId, userId, reason);
        return new ResponseEntity<>(createdFlag, HttpStatus.CREATED);
    }

    /**
     * Endpoint to retrieve all flags (for admin review).
     */
    @GetMapping
    public ResponseEntity<List<Flag>> getAllFlags() {
        List<Flag> flags = flagService.getAllFlags();
        return ResponseEntity.ok(flags);
    }

    /**
     * Endpoint to retrieve flags for a specific BOLO.
     * Example: GET /api/flags/bolo/1
     */
    @GetMapping("/bolo/{boloId}")
    public ResponseEntity<List<Flag>> getFlagsByBolo(@PathVariable Long boloId) {
        List<Flag> flags = flagService.getFlagsByBoloId(boloId);
        return ResponseEntity.ok(flags);
    }

    /**
     * Endpoint to retrieve flags created by a specific user.
     * Example: GET /api/flags/user/2
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Flag>> getFlagsByUser(@PathVariable Long userId) {
        List<Flag> flags = flagService.getFlagsByUserId(userId);
        return ResponseEntity.ok(flags);
    }
}
