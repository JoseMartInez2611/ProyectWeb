package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.QualificationCategoryDTO;
import co.edu.udes.backend.models.QualificationCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface QualificationCategoryMapper {
    QualificationCategoryMapper INSTANCE = Mappers.getMapper(QualificationCategoryMapper.class);

    @Mapping(source = "groupId", target = "group.id")
    @Mapping(source = "academicSubperiodId", target = "academicSubperiod.id")
    QualificationCategory toEntity(QualificationCategoryDTO qualificationCategory);
    List<QualificationCategory> toEntityList(List<QualificationCategoryDTO> qualificationCategories);

    @Mapping(source = "group.id", target = "groupId")
    @Mapping(source = "academicSubperiod.id", target = "academicSubperiodId")
    QualificationCategoryDTO toDto(QualificationCategory qualificationCategory);
    List<QualificationCategoryDTO> toDtoList(List<QualificationCategory> qualificationCategories);
}
