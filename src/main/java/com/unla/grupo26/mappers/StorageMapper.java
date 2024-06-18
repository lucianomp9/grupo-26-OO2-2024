package com.unla.grupo26.mappers;

import com.unla.grupo26.dto.StorageDto;
import com.unla.grupo26.entities.Storage;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface StorageMapper {
        StorageDto storageToStorageDTO(Storage storage);
        Storage StorageDTOToStorage(StorageDto storageDto);
}
