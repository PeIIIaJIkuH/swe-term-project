package com.hotel.controllers;

import com.hotel.models.Room;
import com.hotel.payload.request.RoomRequest;
import com.hotel.payload.response.MessageResponse;
import com.hotel.payload.response.RoomResponse;
import com.hotel.repository.HotelRepository;
import com.hotel.repository.RoomRepository;
import com.hotel.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/rooms")
public class RoomController {
	@Autowired
	RoomRepository roomRepository;

	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	RoomTypeRepository roomTypeRepository;

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

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<?> createRoom(@Valid @RequestBody RoomRequest request) {
		if (!hotelRepository.existsById(request.getHotelId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Hotel is not found!"));
		}
		if (Integer.toString(request.getNumber()).indexOf(Integer.toString(request.getFloor())) != 0) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Incorrect room floor and/or number!"));
		}
		if (!roomTypeRepository.existsById(request.getRoomTypeId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: RoomType is not found!"));
		}
		Room room = new Room(request.getNumber(), request.getFloor(), true, true);
		hotelRepository.findById(request.getHotelId()).get().getRooms().add(room);
		roomRepository.save(room);
		return ResponseEntity.ok(new MessageResponse("Room created successfully!"));
	}

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
		if (!roomRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Room is not found!"));
		}
		roomRepository.deleteById(id);
		return ResponseEntity.ok(new MessageResponse("Room deleted successfully!"));
	}
}
