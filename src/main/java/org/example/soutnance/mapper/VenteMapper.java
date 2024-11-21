package org.example.soutnance.mapper;


import org.example.soutnance.domain.Arbares;
import org.example.soutnance.domain.Ventes;
import org.example.soutnance.dto.request.ArbaresRequest;
import org.example.soutnance.dto.request.VentesRequest;
import org.example.soutnance.dto.response.VentesResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface VenteMapper {

    Ventes toEntity(VentesRequest ventesRequest);
    VentesResponse toResponse(Ventes ventes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Ventes entity, VentesRequest request);
}
