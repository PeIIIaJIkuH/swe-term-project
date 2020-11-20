package com.hotel.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hotel.models.*;
import com.hotel.models.relations.Reservation;
import com.hotel.payload.request.ReceptionistUpdateReservationRequest;
import com.hotel.payload.request.ReservationRequest;
import com.hotel.payload.request.UpdateReservationRequest;
import com.hotel.payload.response.MessageResponse;
import com.hotel.payload.response.ReservationResponse;
import com.hotel.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/reservations")
public class ReservationController {
	@Autowired
	ReservationRepository reservationRepository;

	@Autowired
	RoomTypeRepository roomTypeRepository;

	@Autowired
	HotelRepository hotelRepository;

	@Autowired
	GuestRepository guestRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	RoomRepository roomRepository;

	private ReservationResponse toResponse(Reservation reservation) {
		ReservationResponse response = new ReservationResponse(reservation.getId(),
				reservation.getStart(),
				reservation.getEnd(),
				reservation.getAccepted(),
				reservation.getReason(),
				reservation.getHotel().getId(),
				reservation.getGuest().getId(),
				reservation.getRoomType().getId());
		if (reservation.getReceptionist() != null) {
			response.setReceptionistId(reservation.getReceptionist().getId());
		}
		return response;
	}

	@GetMapping
	public ResponseEntity<?> getAllReservations() {
		List<ReservationResponse> reservations = new ArrayList<>();
		reservationRepository.findAll().forEach(reservation -> {
			reservations.add(toResponse(reservation));
		});
		return ResponseEntity.ok(reservations);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getReservation(@PathVariable Long id) {
		if (!reservationRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Reservation is not found!"));
		}
		Reservation reservation = reservationRepository.findById(id).get();
		return ResponseEntity.ok(toResponse(reservation));
	}

	@PostMapping
	public ResponseEntity<?> createReservation(@Valid @RequestBody ReservationRequest request) {
		if (!hotelRepository.existsById(request.getHotelId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Hotel is not found!"));
		}
		if (!guestRepository.existsById(request.getGuestId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Guest is not found!"));
		}
		if (!roomTypeRepository.existsById(request.getRoomTypeId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: RoomType is not found!"));
		}
		Date date = new Date(System.currentTimeMillis());
		if (!(request.getStart().after(date) && request.getEnd().after(date) &&
				request.getStart().before(request.getEnd()))) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Incorrect dates!"));
		}
		Hotel hotel = hotelRepository.findById(request.getHotelId()).get();
		Guest guest = guestRepository.findById(request.getGuestId()).get();
		RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId()).get();
		Reservation reservation = new Reservation(request.getStart(), request.getEnd(), hotel, guest, roomType);
		reservationRepository.save(reservation);
		return ResponseEntity.ok(new MessageResponse("Reservation created successfully!"));
	}

	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
		if (!reservationRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Reservation is not found!"));
		}
		Reservation reservation = reservationRepository.findById(id).get();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		if (!(now.after(reservation.getStart()) && now.after(reservation.getEnd()))) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Can not delete past reservations!"));
		}
		reservationRepository.deleteById(id);
		return ResponseEntity.ok(new MessageResponse("Reservation deleted successfully!"));
	}

	@PutMapping("/receptionist/{id}")
	public ResponseEntity<?> receptionistUpdateReservation(
			@PathVariable Long id,
			@Valid @RequestBody ReceptionistUpdateReservationRequest request) {
		if (!reservationRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Reservation is not found!"));
		}
		if (!employeeRepository.existsById(request.getReceptionistId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Receptionist is not found!"));
		}
		Reservation reservation = reservationRepository.findById(id).get();
		if (reservation.getAccepted() != null) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Reservation can no longer be changed by receptionist!"));
		}
		reservation.setAccepted(request.getAccepted());
		if (request.getReason() != null) {
			reservation.setReason(request.getReason());
		}
		Employee receptionist = employeeRepository.findById(request.getReceptionistId()).get();
		reservation.setReceptionist(receptionist);
		reservationRepository.save(reservation);
		return ResponseEntity.ok(new MessageResponse("Reservation updated successfully!"));
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateReservation(@PathVariable Long id, @Valid @RequestBody UpdateReservationRequest request) {
		if (!reservationRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Reservation is not found!"));
		}
		Date date = new Date(System.currentTimeMillis());
		if (!(request.getStart().after(date) && request.getEnd().after(date) &&
				request.getStart().before(request.getEnd()))) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Incorrect dates!"));
		}
		if (!roomTypeRepository.existsById(request.getRoomTypeId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: RoomType is not found!"));
		}
		Reservation reservation = reservationRepository.findById(id).get();
		reservation.setStart(request.getStart());
		reservation.setEnd(request.getEnd());
		RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId()).get();
		reservation.setRoomType(roomType);
		reservationRepository.save(reservation);
		return ResponseEntity.ok(new MessageResponse("Reservation updated successfully!"));
	}
}
