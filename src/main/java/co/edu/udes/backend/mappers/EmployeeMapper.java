package co.edu.udes.backend.mappers;

import co.edu.udes.backend.dto.EmployeeDTO;
import co.edu.udes.backend.models.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {BorrowMapper.class,
                MessageMapper.class,
                ReportMapper.class,
                CommunicationMapper.class
        }
)
public interface EmployeeMapper {
    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    Employee toEntity(EmployeeDTO employee);
    List<Employee> toEntityList(List<EmployeeDTO> employees);

    EmployeeDTO toDto(Employee employee);
    List<EmployeeDTO> toDtoList(List<Employee> employees);
}
