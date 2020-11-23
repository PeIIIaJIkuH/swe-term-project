package com.hotel.controllers;

import com.hotel.models.Hotel;
import com.hotel.models.RoomType;
import com.hotel.models.RoomTypeFeature;
import com.hotel.payload.request.RoomTypeRequest;
import com.hotel.payload.response.MessageResponse;
import com.hotel.repository.HotelRepository;
import com.hotel.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/roomTypes")
public class RoomTypeController {
	@Autowired
	RoomTypeRepository roomTypeRepository;

	@Autowired
	HotelRepository hotelRepository;

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

	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@PostMapping
	public ResponseEntity<?> createRoomType(@Valid RoomTypeRequest request) {
		if (!hotelRepository.existsById(request.getHotelId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Hotel is not found!"));
		}
		if (request.getSize() <= 0) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Incorrect size!"));
		}
		if (request.getCapacity() <= 0) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Incorrect capacity!"));
		}
		if (request.getPrice() <= 0) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Incorrect price!"));
		}
		Hotel hotel = hotelRepository.findById(request.getHotelId()).get();
		for (RoomType roomType : hotel.getRoomTypes()) {
			if (roomType.getName().equals(request.getName())) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Error: Room type name is already taken!"));
			}
		}
		RoomType roomType = new RoomType(request.getName(), request.getSize(), request.getCapacity(),
				request.getPrice());
		request.getFeatures().forEach(featureRequest -> {
			RoomTypeFeature feature = new RoomTypeFeature(featureRequest.getName(), featureRequest.getPrice());
			roomType.getFeatures().add(feature);
		});
		roomTypeRepository.save(roomType);
		return ResponseEntity.ok(new MessageResponse("RoomType created successfully!"));
	}
}
