package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AnswerDocumentDTO;
import co.edu.udes.backend.models.AnswerDocument;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ActivityMapper.class})
public interface AnswerDocumentMapper {
    AnswerDocumentMapper INSTANCE = Mappers.getMapper(AnswerDocumentMapper.class);

    AnswerDocument toEntity(AnswerDocumentDTO answerDocument);
    List<AnswerDocument> toEntityList(List<AnswerDocumentDTO> answerDocuments);

    AnswerDocumentDTO toDto(AnswerDocument answerDocument);
    List<AnswerDocumentDTO> toDtoList(List<AnswerDocument> answerDocuments);
}

