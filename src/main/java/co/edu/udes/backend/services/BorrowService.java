package co.edu.udes.backend.services;


import co.edu.udes.backend.dto.BorrowDTO;
import co.edu.udes.backend.mappers.BorrowMapper;
import co.edu.udes.backend.models.*;
import co.edu.udes.backend.repositories.AcademicResourceRepository;
import co.edu.udes.backend.repositories.BorrowRepository;
import co.edu.udes.backend.repositories.LessonRepository;
import co.edu.udes.backend.repositories.ScheduleRepository;
import co.edu.udes.backend.utils.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BorrowService {

    @Autowired
    private BorrowMapper borrowMapper;
    private final ScheduleRepository scheduleRepository;
    private final BorrowRepository borrowRepository;
    private final LessonRepository lessonRepository;
    private final AcademicResourceRepository academicResourceRepository;

    public List<BorrowDTO> getAll() {
        List<Borrow> borrows = borrowRepository.findAll();
        return borrowMapper.toDtoList(borrows);
    }


    public BorrowDTO create(Borrow borrow) {
        System.out.println("Service Create");
        System.out.println(borrow);
        isBorrowed(borrow, null);
        return borrowMapper.toDto(borrowRepository.save(borrow));
    }

    public List<BorrowDTO> createMultiple(List<Borrow> borrows) {
        List<BorrowDTO> newBorrow = new ArrayList<>();
        for (Borrow borrow : borrows) {
            newBorrow.add(create(borrow));
        }
        return newBorrow;
    }

    public BorrowDTO getById(Long id) {
        return borrowMapper.toDto(borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not found with id: " + id)));
    }


    public BorrowDTO update(Long id, Borrow borrow) {
        borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not found with id: " + id));
        borrow.setId(id);
        return borrowMapper.toDto(borrowRepository.save(borrow));
    }

    public void delete(Long id) {
        borrowRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrow not found with id: " + id));
        borrowRepository.deleteById(id);
    }


    public int parseDurationToMinutes(String duration) {
        int totalMinutes = 0;

        duration = duration.toLowerCase();

        if (duration.contains("hora")) {
            String[] parts = duration.split("hora")[0].trim().split(" ");
            totalMinutes += Integer.parseInt(parts[0]) * 60;
        }

        if (duration.contains("minuto")) {
            String[] parts = duration.split("minuto")[0].trim().split(" ");
            String lastPart = parts[parts.length - 1];
            totalMinutes += Integer.parseInt(lastPart);
        }

        return totalMinutes;
    }

    public void validateRoomAvailability(Borrow borrow) {
        LocalDateTime borrowStart = borrow.getBorrowDate();
        LocalDateTime borrowEnd = borrowStart.plusMinutes(parseDurationToMinutes(borrow.getDuration()));

        DayOfWeek borrowDay = borrowStart.getDayOfWeek();
        LocalTime borrowStartTime = borrowStart.toLocalTime();
        LocalTime borrowEndTime = borrowEnd.toLocalTime();

        AcademicResource resource = academicResourceRepository.findById(borrow.getResource().getId())
                .orElseThrow(() -> new RuntimeException("Academic resource not found"));

        Room room = resource.getRoom();

        List<Lesson> lessons = lessonRepository.findByClassroom(room);

        for (Lesson lesson : lessons) {
            Schedule schedule = lesson.getSchedule();
            String borrowDayName = borrowDay.name();
            if (schedule.getDayOfWeek().getDay().equals(borrowDayName)) {

                LocalTime lessonStart = schedule.getStartHour();
                LocalTime lessonEnd = schedule.getEndHour();

                boolean overlaps = !(borrowEndTime.isBefore(lessonStart) || borrowStartTime.isAfter(lessonEnd));
                if (overlaps) {
                    throw new RuntimeException("The room is occupied by a lesson during the requested time.");
                }
            }
        }
    }




    //Recive el objeto lesson y el ID para actualizar
    public void isBorrowed(Borrow borrow, Long excludeId) {
        System.out.println("IsBorrowed");
        System.out.println(borrow);

        List<Borrow> borrows = borrowRepository.findAllByResource_Id(borrow.getResource().getId());

        String dato = academicResourceRepository.getCategory(borrow.getResource().getId());

        System.out.println(dato);

        if(dato.equals("Salon")){
            System.out.println("Es salon?");
            validateRoomAvailability(borrow);
        }

        for (Borrow existingBorrow : borrows) {
            if (excludeId != null && existingBorrow.getId().equals(excludeId)) {
                continue;
            }


            boolean startsBefore = borrow.getBorrowDate().isBefore(existingBorrow.getReturnDate());
            boolean endsAfter = borrow.getReturnDate().isAfter(existingBorrow.getBorrowDate());

            if (startsBefore && endsAfter) {
                throw new RuntimeException(
                        "The resource is already borrowed by another student."
                );
            }
        }
        borrow.getResource().setAvailability(false);

    }

}