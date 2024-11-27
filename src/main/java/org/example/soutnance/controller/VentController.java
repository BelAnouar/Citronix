package org.example.soutnance.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.soutnance.dto.request.VentesRequest;
import org.example.soutnance.dto.response.VentesResponse;
import org.example.soutnance.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;





@RestController
@RequestMapping("/api/vente")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class VentController {

    private final VenteService venteService;

    @PostMapping
    public ResponseEntity<VentesResponse> createVente(@Valid @RequestBody VentesRequest request) {
        VentesResponse response = venteService.createVente(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<Page<VentesResponse>> getAllVentes(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<VentesResponse> response = venteService.getAllVentes(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentesResponse> getVente(@PathVariable Long id) {
        VentesResponse response = venteService.getVente(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentesResponse> updateVente(
            @PathVariable Long id,
            @Valid @RequestBody VentesRequest request) {
        VentesResponse response = venteService.updateVente(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVente(@PathVariable Long id) {
        venteService.deleteVente(id);
        return ResponseEntity.noContent().build();
    }
}
