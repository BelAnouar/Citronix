package org.example.soutnance.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.soutnance.dto.request.ChampsRequest;
import org.example.soutnance.dto.response.ChampResponse;
import org.example.soutnance.service.ChampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/champ")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class ChampController {

    private final ChampService champService;

    @GetMapping
    public ResponseEntity<Page<ChampResponse>> getAllChamps(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize) {
      Pageable pageable = PageRequest.of(pageNo, pageSize);
        return ResponseEntity.ok(champService.getChamps(pageable));
    }
    @PostMapping
    public ResponseEntity<ChampResponse> createChamp(@Valid @RequestBody ChampsRequest champsRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(champService.createChamp(champsRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChampResponse> getChampById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(champService.getChamp(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChampResponse> updateChamp(@PathVariable("id") Long id , @Valid @RequestBody ChampsRequest champsRequest) {
        return ResponseEntity.ok(champService.updateChamp(id, champsRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ChampResponse> deleteChamp(@PathVariable("id") Long id) {
        champService.deleteChamp(id);
        return ResponseEntity.noContent().build();
    }

}
