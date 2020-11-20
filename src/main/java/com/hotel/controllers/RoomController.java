package com.hotel.controllers;

import com.hotel.models.Room;
import com.hotel.payload.response.MessageResponse;
import com.hotel.payload.response.RoomResponse;
import com.hotel.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
	@Autowired
	RoomRepository roomRepository;

	private RoomResponse toResponse(Room room) {
		RoomResponse response = new RoomResponse(room.getId(),
				room.getNumber(),
				room.getFloor(),
				room.getType().getId(),
				room.getEmpty(),
				room.getClear());
		return response;
	}

	@GetMapping
	public ResponseEntity<?> getAllRooms() {
		List<RoomResponse> rooms = new ArrayList<>();
		roomRepository.findAll().forEach(room -> {
			rooms.add(toResponse(room));
		});
		return ResponseEntity.ok(rooms);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getRoom(@PathVariable Long id) {
		if (!roomRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Room is not found!"));
		}
		Room room = roomRepository.findById(id).get();
		return ResponseEntity.ok(toResponse(room));
	}
}
