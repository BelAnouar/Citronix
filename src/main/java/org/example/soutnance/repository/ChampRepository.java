package org.example.soutnance.repository;

import jakarta.validation.constraints.Min;
import org.example.soutnance.domain.Champs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChampRepository extends JpaRepository<Champs,Long> {

     boolean existsByFerme_IdAndSuperficieGreaterThan(Long ferme_id,  Double superficie);
}
