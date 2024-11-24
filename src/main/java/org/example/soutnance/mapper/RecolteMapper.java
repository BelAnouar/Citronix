package org.example.soutnance.mapper;



import org.example.soutnance.domain.Champs;
import org.example.soutnance.domain.Recoltes;
import org.example.soutnance.dto.request.RecoltesRequest;
import org.example.soutnance.dto.response.RecoltesResponse;
import org.mapstruct.*;


@Mapper(componentModel = "spring")
public interface RecolteMapper {

    @Mapping(target = "champ", source = "champ_id" ,qualifiedByName = "toChamp")
    Recoltes toEntity(RecoltesRequest recoltesRequest);

    @Named("toChamp")
    default Champs toChamp(Long id){
        if(id == null) return null;
        var champ = new Champs();
        champ.setId(id);
        return champ;
    }
    RecoltesResponse toResponse(Recoltes recoltes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Recoltes entity, RecoltesRequest request);
}
