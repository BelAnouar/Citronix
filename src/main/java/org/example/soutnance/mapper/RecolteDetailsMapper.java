package org.example.soutnance.mapper;


import org.example.soutnance.domain.Arbares;
import org.example.soutnance.domain.DetailRecoltes;

import org.example.soutnance.dto.request.RecolteDetailRequest;

import org.example.soutnance.dto.response.RecolteDetailResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface RecolteDetailsMapper {

    @Mapping(target = "arbre", source = "arbre_id", qualifiedByName = "toArbre")
    DetailRecoltes toEntity(RecolteDetailRequest request);

    @Named("toArbre")
    default Arbares toArbre(Long id) {
        if (id == null) return null;
        var arbre = new Arbares();
        arbre.setId(id);
        return arbre;
    }

    @Mapping(target = "arbre_id", source = "arbre.id")
    @Mapping(target = "recolte_id", source = "recolte.id")
    RecolteDetailResponse toResponse(DetailRecoltes recolteDetail);


    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget DetailRecoltes entity, RecolteDetailRequest request);
}
