package org.example.soutnance.mapper;



import org.example.soutnance.domain.Fermes;
import org.example.soutnance.dto.request.FermesRequest;
import org.example.soutnance.dto.response.FermesResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring" )
public interface FermeMapper {


    Fermes toEntity(FermesRequest fermesRequest);

    FermesResponse toResponse(Fermes fermes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Fermes entity, FermesRequest request);
}
