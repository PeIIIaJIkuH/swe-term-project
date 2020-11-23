package com.hotel.controllers;

import com.hotel.models.Employee;
import com.hotel.models.Room;
import com.hotel.models.relations.Monitoring;
import com.hotel.payload.request.MonitoringRequest;
import com.hotel.payload.response.MessageResponse;
import com.hotel.payload.response.MonitoringResponse;
import com.hotel.repository.EmployeeRepository;
import com.hotel.repository.MonitoringRepository;
import com.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/monitorings")
public class MonitoringController {
	@Autowired
	MonitoringRepository monitoringRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoomRepository roomRepository;

	private MonitoringResponse toResponse(Monitoring monitoring) {
		MonitoringResponse response = new MonitoringResponse(monitoring.getId(),
				monitoring.getAvailable(),
				monitoring.getDate(),
				monitoring.getReceptionist().getId(),
				monitoring.getRoom().getId());
		return response;
	}

	@GetMapping
	public ResponseEntity<?> getAllMonitorings() {
		List<MonitoringResponse> monitorings = new ArrayList<>();
		monitoringRepository.findAll().forEach(monitoring -> {
			monitorings.add(toResponse(monitoring));
		});
		return ResponseEntity.ok(monitorings);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getMonitoring(@PathVariable Long id) {
		if (!monitoringRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Monitoring is not found!"));
		}
		Monitoring monitoring = monitoringRepository.findById(id).get();
		return ResponseEntity.ok(toResponse(monitoring));
	}

	@PreAuthorize("hasAuthority('ROLE_RECEPTIONIST')")
	@PostMapping
	public ResponseEntity<?> createMonitoring(@Valid @RequestBody MonitoringRequest request) {
		if (!employeeRepository.existsById(request.getReceptionistId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Receptionist is not found!"));
		}
		if (!roomRepository.existsById(request.getRoomId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Room is not found!"));
		}
		Employee receptionist = employeeRepository.findById(request.getReceptionistId()).get();
		Room room = roomRepository.findById(request.getRoomId()).get();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Monitoring monitoring = new Monitoring(room.getClear() && room.getEmpty(), now);
		monitoring.setReceptionist(receptionist);
		monitoring.setRoom(room);
		monitoringRepository.save(monitoring);
		return ResponseEntity.ok(new MessageResponse("Monitoring created successfully!"));
	}
}
