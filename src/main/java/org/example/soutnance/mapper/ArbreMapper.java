package org.example.soutnance.mapper;


import org.example.soutnance.domain.Arbares;
import org.example.soutnance.domain.Champs;
import org.example.soutnance.domain.Fermes;
import org.example.soutnance.dto.request.ArbaresRequest;
import org.example.soutnance.dto.response.ArbaresResponse;
import org.mapstruct.*;

import java.time.LocalDate;
import java.time.Period;

@Mapper(componentModel = "spring")
public interface ArbreMapper {


    @Mapping(target = "champ", source = "champ_id" ,qualifiedByName = "toChamp")
    Arbares toEntity(ArbaresRequest arbaresRequest);

    @Named("toChamp")
    default Champs toChamp(Long id){
        if(id == null) return null;
        var champ = new Champs();
        champ.setId(id);
        return champ;
    }

    @Mapping(target = "age", expression = "java(calculateAge(arbre.getDatePlantation()))")
    ArbaresResponse toResponse(Arbares arbares);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Arbares entity, ArbaresRequest request);

    @Named("calculateAge")
    default int calculateAge(LocalDate datePlantation) {
        return Period.between(datePlantation, LocalDate.now()).getYears();
    }
}
