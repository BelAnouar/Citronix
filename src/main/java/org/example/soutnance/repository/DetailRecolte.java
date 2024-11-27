package org.example.soutnance.repository;


import org.example.soutnance.domain.DetailRecoltes;
import org.example.soutnance.domain.enums.Saison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DetailRecolte extends JpaRepository<DetailRecoltes,Long > {

    boolean existsByArbre_IdAndRecolte_Saison(Long arbreId, Saison saison);

    List<DetailRecoltes> findAllByRecolteId(Long recolteId);

    @Query("SELECT SUM(rd.quantite) FROM DetailRecoltes rd WHERE rd.recolte.id = :recolteId")
    Double calculateTotalQuantityForRecolte(@Param("recolteId") Long recolteId);

}
