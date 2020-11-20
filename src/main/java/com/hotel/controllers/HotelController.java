package com.hotel.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hotel.models.*;
import com.hotel.payload.request.HotelRequest;
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
        return ResponseEntity.ok(toResponse(hotel));
    }

    @PostMapping
    public ResponseEntity<?> createHotel(@Valid @RequestBody HotelRequest request) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        System.out.println(gson.toJson(request));
        if (hotelRepository.existsByName(request.getName())) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Name is already taken!"));
        }
        Hotel hotel = new Hotel(request.getName(),
                request.getCountry(),
                request.getCity(),
                request.getStreet());
        request.getNumbers().forEach(number -> {
            hotel.getNumbers().add(number);
        });
        request.getFeatures().forEach(feature -> {
            hotel.getFeatures().add(feature);
        });
        for (String seasonName : request.getSeasons()) {
            if (!seasonRepository.existsByName(seasonName)) {
                return ResponseEntity.badRequest()
                        .body(new MessageResponse("Error: Season is not found!"));
            }
            Season season = seasonRepository.findByName(seasonName);
            hotel.getSeasons().add(season);
        }
        request.getRoomTypes().forEach(roomTypeRequest -> {
            RoomType roomType = new RoomType(roomTypeRequest.getName(),
                    roomTypeRequest.getSize(),
                    roomTypeRequest.getCapacity(),
                    roomTypeRequest.getPrice());
            roomTypeRequest.getFeatures().forEach(featureRequest -> {
                RoomTypeFeature feature = new RoomTypeFeature(featureRequest.getName(),
                        featureRequest.getPrice());
                roomType.getFeatures().add(feature);
            });
            roomTypeRequest.getRooms().forEach(roomRequest -> {
                Room room = new Room(roomRequest.getNumber(),
                        roomRequest.getFloor(),
                        true,
                        true);
                room.setType(roomType);
                hotel.getRooms().add(room);
            });
            hotel.getRoomTypes().add(roomType);
        });
        hotelRepository.save(hotel);
        return ResponseEntity.ok(new MessageResponse("Hotel created successfully!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteHotel(@PathVariable Long id) {
        if (!hotelRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Hotel is not found!"));
        }
        Hotel hotel = hotelRepository.findById(id).get();
        hotelRepository.delete(hotel);
        return ResponseEntity.ok(new MessageResponse("Hotel deleted successfully!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateHotel(@PathVariable Long id, @Valid @RequestBody HotelRequest request) {
        if (!hotelRepository.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Hotel is not found!"));
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
        for (String season : request.getSeasons()) {
            if (!seasonRepository.existsByName(season)) {
                return ResponseEntity.badRequest()
                        .body(new MessageResponse("Error: Season is not found!"));
            }
            hotel.getSeasons().add(seasonRepository.findByName(season));
        }
        hotel.getRoomTypes().clear();
        request.getRoomTypes().forEach(roomTypeRequest -> {
            RoomType roomType = new RoomType(roomTypeRequest.getName(),
                    roomTypeRequest.getSize(),
                    roomTypeRequest.getCapacity(),
                    roomTypeRequest.getPrice());
            roomTypeRequest.getFeatures().forEach(featureRequest -> {
                RoomTypeFeature feature = new RoomTypeFeature(featureRequest.getName(),
                        featureRequest.getPrice());
            });
            roomTypeRequest.getRooms().forEach(roomRequest -> {
                Room room = new Room(roomRequest.getNumber(),
                        roomRequest.getFloor(),
                        true,
                        true);
                room.setType(roomType);
                hotel.getRooms().add(room);
            });
            hotel.getRoomTypes().add(roomType);
        });
        hotelRepository.save(hotel);
        return ResponseEntity.ok(new MessageResponse("Hotel updated successfully!"));
    }
}
