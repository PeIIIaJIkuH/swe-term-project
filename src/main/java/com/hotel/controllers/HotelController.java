package com.hotel.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hotel.models.*;
import com.hotel.payload.request.HotelRequest;
import com.hotel.payload.request.RoomRequest;
import com.hotel.payload.request.RoomTypeFeatureRequest;
import com.hotel.payload.request.RoomTypeRequest;
import com.hotel.payload.response.HotelDetailedResponse;
import com.hotel.payload.response.HotelMainPageResponse;
import com.hotel.payload.response.HotelResponse;
import com.hotel.payload.response.MessageResponse;
import com.hotel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/hotels")
public class HotelController {
	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	SeasonRepository seasonRepository;

	@Autowired
	RoomRepository roomRepository;

	@Autowired
	RoomTypeRepository roomTypeRepository;

	@Autowired
	ReservationRepository reservationRepository;

	private HotelResponse toResponse(Hotel hotel) {
		HotelResponse response = new HotelResponse(hotel.getId(),
				hotel.getName(),
				hotel.getCountry(),
				hotel.getCity(),
				hotel.getStreet());
		hotel.getFeatures().forEach(feature -> {
			response.getFeatures().add(feature);
		});
		hotel.getNumbers().forEach(number -> {
			response.getNumbers().add(number);
		});
		hotel.getSeasons().forEach(season -> {
			response.getSeasonIds().add(season.getId());
		});
		hotel.getRoomTypes().forEach(roomType -> {
			response.getRoomTypeIds().add(roomType.getId());
		});
		hotel.getRooms().forEach(room -> {
			response.getRoomIds().add(room.getId());
		});
		return response;
	}

	private HotelDetailedResponse toDetailedResponse(Hotel hotel) {
		HotelDetailedResponse response = new HotelDetailedResponse(hotel.getId(),
				hotel.getName(),
				hotel.getCountry(),
				hotel.getCity(),
				hotel.getStreet(),
				hotel.getFeatures(),
				hotel.getNumbers(),
				hotel.getRoomTypes());
		return response;
	}

	@GetMapping
	public ResponseEntity<?> getAllHotels() {
		List<HotelResponse> hotels = new ArrayList<>();
		hotelRepository.findAll().forEach(hotel -> {
			toResponse(hotel);
			hotels.add(toResponse(hotel));
		});
		return ResponseEntity.ok(hotels);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getHotel(@PathVariable Long id) {
		if (!hotelRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Hotel is not found!"));
		}
		Hotel hotel = hotelRepository.findById(id).get();
		return ResponseEntity.ok(toDetailedResponse(hotel));
	}

	@GetMapping("/main")
	public ResponseEntity<?> getHotelsMainPage() {
		List<HotelMainPageResponse> hotels = new ArrayList<>();
		hotelRepository.findAll().forEach(hotel -> {
			int sum = 0;
			for (RoomType roomType : hotel.getRoomTypes()) {
				sum += roomType.getPrice();
			}
			HotelMainPageResponse response = new HotelMainPageResponse(hotel.getId(),
					hotel.getName(),
					hotel.getCountry(),
					hotel.getCity(),
					sum / hotel.getRoomTypes().size());
			hotels.add(response);
		});
		return ResponseEntity.ok(hotels);
	}

	@PostMapping
	public ResponseEntity<?> createHotel(@Valid @RequestBody HotelRequest request) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		if (hotelRepository.existsByName(request.getName())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Name is already taken!"));
		}
		for (String number : request.getNumbers()) {
			for (Hotel hotel : hotelRepository.findAll()) {
				if (hotel.getNumbers().contains(number)) {
					return ResponseEntity.badRequest()
							.body(new MessageResponse("Error: Phone is already taken!"));
				}
			}
		}
		Hotel hotel = new Hotel(request.getName(),
				request.getCountry(),
				request.getCity(),
				request.getStreet());
		request.getFeatures().forEach(feature -> {
			hotel.getFeatures().add(feature);
		});
		request.getNumbers().forEach(number -> {
			hotel.getNumbers().add(number);
		});
		for (String seasonName : request.getSeasons()) {
			if (!seasonRepository.existsByName(seasonName)) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Error: Season is not found!"));
			}
			Season season = seasonRepository.findByName(seasonName);
			hotel.getSeasons().add(season);
		}
		for (RoomTypeRequest roomTypeRequest : request.getRoomTypes()) {
			for (RoomType roomType : hotel.getRoomTypes()) {
				if (roomType.getName().equals(roomTypeRequest.getName())) {
					return ResponseEntity.badRequest()
							.body(new MessageResponse("Error: Room type name is already taken!"));
				}
			}
			RoomType roomType = new RoomType(roomTypeRequest.getName(),
					roomTypeRequest.getSize(),
					roomTypeRequest.getCapacity(),
					roomTypeRequest.getPrice());
			for (RoomTypeFeatureRequest featureRequest : roomTypeRequest.getFeatures()) {
				RoomTypeFeature feature = new RoomTypeFeature(featureRequest.getName(),
						featureRequest.getPrice());
				roomType.getFeatures().add(feature);
			}
			for (RoomRequest roomRequest : roomTypeRequest.getRooms()) {
				if (Integer.toString(roomRequest.getNumber()).indexOf(Integer.toString(roomRequest.getFloor())) != 0) {
					return ResponseEntity.badRequest()
							.body(new MessageResponse("Error: Incorrect room floor and/or number!"));
				}
				Room room = new Room(roomRequest.getNumber(), roomRequest.getFloor(), true, true);
				room.setType(roomType);
				hotel.getRooms().add(room);
			}
			hotel.getRoomTypes().add(roomType);
		}
		hotelRepository.save(hotel);
		return ResponseEntity.ok(new MessageResponse("Hotel created successfully!"));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteHotel(@PathVariable Long id) {
		if (!hotelRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Hotel is not found!"));
		}
		hotelRepository.deleteById(id);
		return ResponseEntity.ok(new MessageResponse("Hotel deleted successfully!"));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateHotel(@PathVariable Long id, @Valid @RequestBody HotelRequest request) {
		if (!hotelRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Hotel is not found!"));
		}
		for (String number : request.getNumbers()) {
			for (Hotel hotel : hotelRepository.findAll()) {
				if (hotel.getId() != hotelRepository.findById(id).get().getId()) {
					if (hotel.getNumbers().contains(number)) {
						return ResponseEntity.badRequest()
								.body(new MessageResponse("Error: Phone is already taken!"));
					}
				}
			}
		}
		Hotel hotel = hotelRepository.findById(id).get();
		hotel.setName(request.getName());
		hotel.setCountry(request.getCountry());
		hotel.setCity(request.getCity());
		hotel.setStreet(request.getStreet());
		hotel.getFeatures().clear();
		request.getFeatures().forEach(feature -> {
			hotel.getFeatures().add(feature);
		});
		hotel.getNumbers().clear();
		request.getNumbers().forEach((number -> {
			hotel.getNumbers().add(number);
		}));
		hotel.getSeasons().clear();
		for (String season : request.getSeasons()) {
			if (!seasonRepository.existsByName(season)) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Error: Season is not found!"));
			}
			hotel.getSeasons().add(seasonRepository.findByName(season));
		}
		hotel.getRoomTypes().clear();
		hotel.getRooms().clear();
		for (RoomTypeRequest roomTypeRequest : request.getRoomTypes()) {
			for (RoomType roomType : hotel.getRoomTypes()) {
				if (roomType.getName().equals(roomTypeRequest.getName())) {
					return ResponseEntity.badRequest()
							.body(new MessageResponse("Error: Room type name is already taken!"));
				}
			}
			RoomType roomType = new RoomType(roomTypeRequest.getName(),
					roomTypeRequest.getSize(),
					roomTypeRequest.getCapacity(),
					roomTypeRequest.getPrice());
			for (RoomTypeFeatureRequest featureRequest : roomTypeRequest.getFeatures()) {
				RoomTypeFeature feature = new RoomTypeFeature(featureRequest.getName(),
						featureRequest.getPrice());
				roomType.getFeatures().add(feature);
			}
			for (RoomRequest roomRequest : roomTypeRequest.getRooms()) {
				if (Integer.toString(roomRequest.getNumber()).indexOf(Integer.toString(roomRequest.getFloor())) != 0) {
					return ResponseEntity.badRequest()
							.body(new MessageResponse("Error: Incorrect room floor and/or number!"));
				}
				Room room = new Room(roomRequest.getNumber(), roomRequest.getFloor(), true, true);
				room.setType(roomType);
				hotel.getRooms().add(room);
			}
			hotel.getRoomTypes().add(roomType);
		}
		hotelRepository.save(hotel);
		return ResponseEntity.ok(new MessageResponse("Hotel updated successfully!"));
	}
}
