package co.edu.udes.backend.services;

import co.edu.udes.backend.dto.CareerDTO;
import co.edu.udes.backend.mappers.CareerMapper;
import co.edu.udes.backend.models.Career;
import co.edu.udes.backend.repositories.CareerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CareerService {

    private final CareerRepository careerRepository;
    @Autowired
    private CareerMapper careerMapper;

    public List<CareerDTO> getAll() {
        List<Career> careers = careerRepository.findAll();
        return careerMapper.toDtoList(careers);
    }

    public CareerDTO getById(Long id) {
        return careerMapper.toDto(careerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Career not found with id: " + id)));
    }

    public CareerDTO create(Career career) {
        return careerMapper.toDto(
                careerRepository.save(career)
        );
    }

    public List<CareerDTO> createMultiple(List<Career> careers) {
        return careerMapper.toDtoList(
                careerRepository.saveAll(careers)
        );
    }

    public CareerDTO update(Long id, Career career) {
        careerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Career not found with id: " + id));
        career.setId(id);
        return careerMapper.toDto(
                careerRepository.save(career)
        );
    }

    public void delete(Long id) {
        careerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Career not found with id: " + id));
        careerRepository.deleteById(id);
    }

}
