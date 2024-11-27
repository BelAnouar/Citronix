package org.example.soutnance.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.soutnance.dto.request.RecoltesRequest;
import org.example.soutnance.dto.response.RecoltesResponse;
import org.example.soutnance.service.RecolteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recoltes")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class RecolteController {

    private final RecolteService recolteService;

    @PostMapping
    public ResponseEntity<RecoltesResponse> createRecolte(@Valid @RequestBody RecoltesRequest request) {
        RecoltesResponse response = recolteService.createRecolte(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<RecoltesResponse>> getRecoltes(@RequestParam(defaultValue = "0") int pageNo,@RequestParam(defaultValue = "10")int pageSize ) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<RecoltesResponse> recoltes = recolteService.getRecoltes(pageable);
        return ResponseEntity.ok(recoltes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecoltesResponse> getRecolte(@PathVariable Long id) {
        RecoltesResponse response = recolteService.getRecolte(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecoltesResponse> updateRecolte(
            @PathVariable Long id,
            @Valid @RequestBody RecoltesRequest request) {
        RecoltesResponse response = recolteService.updateRecolte(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecolte(@PathVariable Long id) {
        recolteService.deleteRecolte(id);
        return ResponseEntity.noContent().build();
    }
}
