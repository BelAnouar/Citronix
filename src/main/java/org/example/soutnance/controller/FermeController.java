package org.example.soutnance.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.soutnance.dto.request.FermesRequest;
import org.example.soutnance.dto.response.FermesResponse;
import org.example.soutnance.service.FermeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ferme")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class FermeController {

    private final FermeService fermeService;

    @PostMapping
    public ResponseEntity<FermesResponse> createFerme(@Valid @RequestBody FermesRequest fermesRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(fermeService.createFermeRepository(fermesRequest));
    }

    @GetMapping
    public ResponseEntity<Page<FermesResponse>> getFermes(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable= PageRequest.of(pageNo, pageSize);
        return ResponseEntity.ok(fermeService.findAllFermes(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FermesResponse> getFermeById(@PathVariable Long id) {
        return ResponseEntity.ok(fermeService.findFermeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FermesResponse> updateFErme(@PathVariable Long id, @Valid @RequestBody FermesRequest fermesRequest) {
        return ResponseEntity.ok(fermeService.updateFermeById(id, fermesRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FermesResponse> deleteFerme(@PathVariable Long id) {
        fermeService.deleteFermeById(id);
        return ResponseEntity.noContent().build();
    }
}
