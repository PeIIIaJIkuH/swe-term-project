package com.hotel.controllers;

import com.hotel.models.Employee;
import com.hotel.models.Room;
import com.hotel.models.relations.Cleaning;
import com.hotel.payload.request.CleaningRequest;
import com.hotel.payload.response.CleaningResponse;
import com.hotel.payload.response.MessageResponse;
import com.hotel.repository.CleaningRepository;
import com.hotel.repository.EmployeeRepository;
import com.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/cleanings")
public class CleaningController {
	@Autowired
	CleaningRepository cleaningRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoomRepository roomRepository;

	private CleaningResponse toResponse(Cleaning cleaning) {
		CleaningResponse response = new CleaningResponse(cleaning.getId(),
				cleaning.getDate(),
				cleaning.getCleaningWorker().getId(),
				cleaning.getRoom().getId());
		return response;
	}

	@GetMapping
	public ResponseEntity<?> getAllCleanings() {
		List<CleaningResponse> cleanings = new ArrayList<>();
		cleaningRepository.findAll().forEach(cleaning -> {
			cleanings.add(toResponse(cleaning));
		});
		return ResponseEntity.ok(cleanings);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getCleaning(@PathVariable Long id) {
		if (!cleaningRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Cleaning worker is not found!"));
		}
		Cleaning cleaning = cleaningRepository.findById(id).get();
		return ResponseEntity.ok(toResponse(cleaning));
	}

	@PostMapping
	public ResponseEntity<?> createCleaning(@Valid @RequestBody CleaningRequest request) {
		if (!employeeRepository.existsById(request.getCleaningWorkerId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Cleaning worker is not found!"));
		}
		if (!roomRepository.existsById(request.getRoomId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Room is not found!"));
		}
		Employee cleaningWorker = employeeRepository.findById(request.getCleaningWorkerId()).get();
		Room room = roomRepository.findById(request.getRoomId()).get();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Cleaning cleaning = new Cleaning(now);
		cleaning.setCleaningWorker(cleaningWorker);
		cleaning.setRoom(room);
		room.setClear(true);
		cleaningRepository.save(cleaning);
		roomRepository.save(room);
		return ResponseEntity.ok(new MessageResponse("Cleaning created successfully!"));
	}
}
