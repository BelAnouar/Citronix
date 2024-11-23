package org.example.soutnance.mapper;


import org.example.soutnance.domain.Champs;
import org.example.soutnance.domain.Fermes;
import org.example.soutnance.dto.request.ChampsRequest;
import org.example.soutnance.dto.response.ChampResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface ChampMapper {



    @Mapping(target = "ferme", source = "ferme_id", qualifiedByName = "mapToFermes")
    Champs toEntity(ChampsRequest champsRequest);


    @Named("mapToFermes")
    default Fermes map(Long id) {
        if (id == null) return null;
        Fermes fermes = new Fermes();
        fermes.setId(id);
        return fermes;
    }


    ChampResponse toResponse(Champs champ);



    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Champs entity, ChampsRequest request);
}
