package org.example.soutnance.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.soutnance.dto.request.RecolteDetailRequest;
import org.example.soutnance.dto.response.RecolteDetailResponse;
import org.example.soutnance.service.RecolteDetails;
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
@RequestMapping("/api/RecolteDetails")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Validated
public class RecolteDetailsController {


    private final RecolteDetails recolteDetails;
    @PostMapping
    public ResponseEntity<RecolteDetailResponse>  createRecolteDetail(@Valid @RequestBody RecolteDetailRequest recolteDetailRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recolteDetails.createRecolteDetail(recolteDetailRequest));
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecolteDetailResponse> getRecolteDetail(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(recolteDetails.getRecolteDetails(id));
    }
    @GetMapping
    public ResponseEntity<Page<RecolteDetailResponse>> getRecolteDetails(@RequestParam(defaultValue = "0") int pageNo ,@RequestParam(defaultValue = "10") int pageSize) {
        Pageable pageable= PageRequest.of(pageNo, pageSize);
        return  ResponseEntity.ok(recolteDetails.getAllRecolteDetails(pageable));
    }

}
