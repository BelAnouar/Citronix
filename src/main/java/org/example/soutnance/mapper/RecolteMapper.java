package org.example.soutnance.mapper;



import org.example.soutnance.domain.Recoltes;
import org.example.soutnance.dto.request.RecoltesRequest;
import org.example.soutnance.dto.response.RecoltesResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel = "spring")
public interface RecolteMapper {


    Recoltes toEntity(RecoltesRequest recoltesRequest);

    RecoltesResponse toResponse(Recoltes recoltes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Recoltes entity, RecoltesRequest request);
}
