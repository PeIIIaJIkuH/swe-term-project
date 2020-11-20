package com.hotel.controllers;

import com.hotel.models.RoomType;
import com.hotel.payload.response.MessageResponse;
import com.hotel.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/roomTypes")
public class RoomTypeController {
	@Autowired
	RoomTypeRepository roomTypeRepository;

	@GetMapping
	public ResponseEntity<?> getAllRoomTypes() {
		return ResponseEntity.ok(roomTypeRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getRoomType(@PathVariable Long id) {
		if (!roomTypeRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: RoomType is not found!"));
		}
		RoomType roomType = roomTypeRepository.findById(id).get();
		return ResponseEntity.ok(roomType);
	}
}
