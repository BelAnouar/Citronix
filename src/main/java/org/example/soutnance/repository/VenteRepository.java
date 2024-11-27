package org.example.soutnance.repository;

import org.example.soutnance.domain.Ventes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VenteRepository extends JpaRepository<Ventes,Long> {
    @Query("SELECT SUM(v.quantite) FROM Ventes v WHERE v.recolte.id = :recolteId")
    Optional<Double> calculateTotalQuantityForRecolte(@Param("recolteId") Long recolteId);
}
