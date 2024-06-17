package com.unla.grupo26.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.unla.grupo26.dto.CrearLoteDTO;
import com.unla.grupo26.entities.Lote;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LoteMapper {

    LoteMapper INSTANCE = Mappers.getMapper(LoteMapper.class);
    
    @Mapping(target = "almacen", ignore = true) 
    Lote crearLoteDTOToLote(CrearLoteDTO crearLoteDTO);

    List<Lote> crearLoteDTOsToLotes(List<CrearLoteDTO> crearLoteDTOs);
}