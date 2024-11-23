package org.example.soutnance.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.soutnance.dto.request.ArbaresRequest;
import org.example.soutnance.dto.response.ArbaresResponse;
import org.example.soutnance.service.ArbreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/arbre")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class ArbareController {

     private  final ArbreService arbreService;


    @PostMapping
    public ResponseEntity<ArbaresResponse> createArbare(@Valid @RequestBody ArbaresRequest arbaresRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(arbreService.createArbre(arbaresRequest));
    }

    @GetMapping
    public ResponseEntity<Page<ArbaresResponse>> getArbares(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return ResponseEntity.ok(arbreService.getArbres(pageable));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ArbaresResponse> getArbare(@PathVariable Long id) {
        return ResponseEntity.ok(arbreService.getArbre(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArbaresResponse> updateArbare(@PathVariable Long id, @Valid @RequestBody ArbaresRequest arbaresRequest) {
        return ResponseEntity.ok(arbreService.updateArbre(id, arbaresRequest));
    }




}
