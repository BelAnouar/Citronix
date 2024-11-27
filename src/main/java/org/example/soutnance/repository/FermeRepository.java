package org.example.soutnance.repository;

import org.example.soutnance.domain.Fermes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FermeRepository extends JpaRepository<Fermes ,Long> {
    boolean existsByNom(String nom);

    boolean existsByNomAndIdNot(String Nom, Long id);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM Champs c WHERE c.ferme.id = :fermeId")
    boolean hasFarmAnyFields(@Param("fermeId") Long fermeId);

    @Query("SELECT COALESCE(SUM(c.superficie), 0) FROM Champs c WHERE c.ferme.id = :fermeId")
    double calculateTotalFieldsArea(@Param("fermeId") Long fermeId);
}
