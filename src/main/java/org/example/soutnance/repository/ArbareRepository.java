package org.example.soutnance.repository;

import org.example.soutnance.domain.Arbares;
import org.example.soutnance.domain.Champs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArbareRepository extends JpaRepository<Arbares,Long> {
    long countByChamp(Champs champ);
}
