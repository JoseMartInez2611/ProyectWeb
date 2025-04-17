package co.edu.udes.backend.mappers;


import co.edu.udes.backend.dto.inheritanceDTO.CommunicationDTO;
import co.edu.udes.backend.models.inheritance.Communication;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {ProfileUMapper.class}
)

public interface CommunicationMapper {
    CommunicationMapper INSTANCE = Mappers.getMapper(CommunicationMapper.class);

    @Mapping(source = "receiverIds", target = "receivers")
    Communication toEntity(CommunicationDTO communication);
    List<Communication> toEntityList(List<CommunicationDTO> communications);

    @Mapping(source = "receivers", target = "receiverIds")
    CommunicationDTO toDto(Communication communication);
    List<CommunicationDTO> toDtoList(List<Communication> communications);
}