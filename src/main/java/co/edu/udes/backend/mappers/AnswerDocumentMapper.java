package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.AnswerDocumentDTO;
import co.edu.udes.backend.models.AnswerDocument;

import java.util.Collections;
import java.util.List;

public class AnswerDocumentMapper {
    private final ActivityMapper activityMapper = new ActivityMapper();

    public AnswerDocumentDTO toDTO(AnswerDocument answerDocument) {
        return AnswerDocumentDTO.builder()
                .id(answerDocument.getId())
                .fileName(answerDocument.getFileName())
                .filePath(answerDocument.getFilePath())
                .activity(activityMapper.toDTO(answerDocument.getActivity()))
                .build();
    }

    public AnswerDocument toEntity(AnswerDocumentDTO answerDocumentDTO) {
        return AnswerDocument.builder()
                .id(answerDocumentDTO.getId())
                .fileName(answerDocumentDTO.getFileName())
                .filePath(answerDocumentDTO.getFilePath())
                .activity(activityMapper.toEntity(answerDocumentDTO.getActivity()))
                .build();
    }

    public List<AnswerDocument> toEntityList(List<AnswerDocumentDTO> dtos) {
        if (dtos == null || dtos.isEmpty()) {
            return Collections.emptyList();
        }

        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }

    public List<AnswerDocumentDTO> toDTOList(List<AnswerDocument> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        return entities.stream()
                .map(this::toDTO)
                .toList();
    }

}
