package co.edu.udes.backend.services;


import co.edu.udes.backend.dto.RoomDTO;
import co.edu.udes.backend.mappers.RoomMapper;
import co.edu.udes.backend.models.AcademicResource;
import co.edu.udes.backend.models.Room;
import co.edu.udes.backend.repositories.LessonRepository;
import co.edu.udes.backend.repositories.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoomService {


    private final AcademicResourceService academicResourceService;
    private final LessonRepository lessonRepository;
    private final RoomRepository roomRepository;
    @Autowired
    private RoomMapper roomMapper;

    public List<RoomDTO> getAll() {
        List<Room> rooms = roomRepository.findAll();
        return roomMapper.toDtoList(rooms);
    }

    public RoomDTO create(Room room) {

        Room newRoom = roomRepository.save(room);
        roomToResource(newRoom);

        return roomMapper.toDto(newRoom);
    }

    public List<RoomDTO> createMultiple(List<Room> rooms) {
        List<RoomDTO> newRoom = new ArrayList<>();
        for (Room room : rooms) {
            newRoom.add(create(room));
        }
        return newRoom;
    }

    public RoomDTO getById(Long id) {
        return roomMapper.toDto(roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id)));
    }



    public RoomDTO update(Long id, Room room) {
        roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        room.setId(id);
        return roomMapper.toDto(roomRepository.save(room));
    }

    public void delete(Long id) {
        roomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Room not found with id: " + id));
        roomRepository.deleteById(id);
    }

    public List<String> getAllRoomSchedule(long id){
        return lessonRepository.getSchedulesByRoomIdFromLesson(id);
    }

    public void roomToResource(Room room){

        String concat = room.getBuilding()+"-"+room.getFloor()+ room.getNumber();
        AcademicResource recursos = new AcademicResource(0L, concat, "Área especializada para impartir y difundir el conocimiento.", "Salon", true, room);
        academicResourceService.create(recursos);

    }
}
