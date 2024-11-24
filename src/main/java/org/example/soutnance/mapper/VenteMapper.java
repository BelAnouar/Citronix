package org.example.soutnance.mapper;


import org.example.soutnance.domain.Arbares;
import org.example.soutnance.domain.Recoltes;
import org.example.soutnance.domain.Ventes;
import org.example.soutnance.dto.request.ArbaresRequest;
import org.example.soutnance.dto.request.VentesRequest;
import org.example.soutnance.dto.response.VentesResponse;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface VenteMapper {

    @Mapping(target = "recolte",source = "recolte_id",qualifiedByName = "toRecolte")
    Ventes toEntity(VentesRequest ventesRequest);

    @Named("toRecolte")
    default Recoltes toRecolte(Long id){
        if(id == null) return null;
        var recoltes = new Recoltes();
        recoltes.setId(id);
        return recoltes;
    }

    VentesResponse toResponse(Ventes ventes);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntity(@MappingTarget Ventes entity, VentesRequest request);
}
