package pawnbolo.com.pawnbolo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pawnbolo.com.pawnbolo.models.Bolo;
import pawnbolo.com.pawnbolo.models.BoloType;
import pawnbolo.com.pawnbolo.services.BoloService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bolos")
public class BoloController {
    private final BoloService boloService;

    @Autowired
    public BoloController(BoloService boloService) {
        this.boloService = boloService;
    }

    @GetMapping("/{boloId}")
    public ResponseEntity<Optional<Bolo>> getBoloById(@PathVariable Long boloId) {
        return ResponseEntity.ok(boloService.getBoloById(boloId));
    }

    @GetMapping("/type/{boloType}")
    public ResponseEntity<List<Bolo>> getBolosByType(@PathVariable BoloType boloType) {
        return ResponseEntity.ok(boloService.getBolosByType(boloType));
    }

    @GetMapping("/serial/{serialNumber}")
    public ResponseEntity<Optional<Bolo>> getBoloBySerialNumber(@PathVariable String serialNumber) {
        return ResponseEntity.ok(boloService.getBoloBySerialNumber(serialNumber));
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<List<Bolo>> getBolosByModel(@PathVariable String model) {
        return ResponseEntity.ok(boloService.getBolosByModel(model));
    }

//MAybe add some for get person by name


    @PostMapping
    public ResponseEntity<Bolo> createBolo(@RequestBody Bolo bolo) {
        Bolo createdBolo = boloService.createBolo(bolo);
        return new ResponseEntity<>(createdBolo, HttpStatus.CREATED);
    }
}
