package com.hotel.controllers;

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
import org.springframework.security.access.prepost.PreAuthorize;
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

	@PreAuthorize("hasAuthority('TYPE_GUEST') or hasAuthority('ROLE_RECEPTIONIST')")
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
		Integer roomCount = 0;
		for (Room room : hotel.getRooms()) {
			RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId()).get();
			if (room.getType().getId() == roomType.getId()) {
				roomCount++;
			}
		}
		RoomType roomType = roomTypeRepository.findById(request.getRoomTypeId()).get();
		Integer blockedRoomCount = 0;
		for (Reservation reservationFromDb : reservationRepository.findAll()) {
			if (reservationFromDb.getHotel().getId() == hotel.getId()) {
				if (roomType.getId() == reservationFromDb.getRoomType().getId()) {
					if (!((request.getStart().after(reservationFromDb.getStart()) &&
							request.getStart().after(reservationFromDb.getEnd())) ||
							(request.getEnd().before(reservationFromDb.getStart())) &&
									request.getEnd().before(reservationFromDb.getEnd()))) {
						blockedRoomCount++;
					}
				}
			}
		}
		if (roomCount - blockedRoomCount <= 0) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: No available rooms!"));
		}
		Guest guest = guestRepository.findById(request.getGuestId()).get();
		Reservation reservation = new Reservation(request.getStart(), request.getEnd(), hotel, guest, roomType);
		reservationRepository.save(reservation);
		return ResponseEntity.ok(new MessageResponse("Reservation created successfully!"));
	}

	@PreAuthorize("hasAuthority('TYPE_GUEST') or hasAuthority('ROLE_RECEPTIONIST')")
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteReservation(@PathVariable Long id) {
		if (!reservationRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Reservation is not found!"));
		}
		Reservation reservation = reservationRepository.findById(id).get();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		if (reservation.getStart().before(now) || reservation.getEnd().before(now)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Can not delete past reservations!"));
		}
		reservationRepository.deleteById(id);
		return ResponseEntity.ok(new MessageResponse("Reservation deleted successfully!"));
	}

	@PreAuthorize("hasAuthority('ROLE_RECEPTIONIST')")
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
		Employee receptionist = employeeRepository.findById(request.getReceptionistId()).get();
		Integer roomCount = 0;
		Hotel hotel = hotelRepository.findById(receptionist.getHotel().getId()).get();
		for (Room room : hotel.getRooms()) {
			if (room.getType().getName().equals(reservation.getRoomType().getName())) {
				roomCount++;
			}
		}
		Integer blockedRoomCount = 0;
		for (Reservation reservationFromDb : reservationRepository.findAll()) {
			if (reservation.getId() != reservationFromDb.getId()) {
				if (reservationFromDb.getHotel().getId() == hotel.getId()) {
					if (reservation.getRoomType().getId() == reservationFromDb.getRoomType().getId()) {
						if (!((reservation.getStart().after(reservationFromDb.getStart()) &&
								reservation.getStart().after(reservationFromDb.getEnd())) ||
								(reservation.getEnd().before(reservationFromDb.getStart())) &&
										reservation.getEnd().before(reservationFromDb.getEnd()))) {
							blockedRoomCount++;
						}
					}
				}
			}
		}
		if (blockedRoomCount >= roomCount) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: RoomTypes are already taken!"));
		}
		reservation.setAccepted(request.getAccepted());
		if (request.getReason() != null) {
			reservation.setReason(request.getReason());
		}
		reservation.setReceptionist(receptionist);
		reservationRepository.save(reservation);
		return ResponseEntity.ok(new MessageResponse("Reservation updated successfully!"));
	}

	@PreAuthorize("hasAuthority('TYPE_GUEST')")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateReservation(@PathVariable Long id,
	                                           @Valid @RequestBody UpdateReservationRequest request) {
		if (!reservationRepository.existsById(id)) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Reservation is not found!"));
		}
		if (!roomTypeRepository.existsById(request.getRoomTypeId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: RoomType is not found!"));
		}
		if (!guestRepository.existsById(request.getGuestId())) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: Guest is not found!"));
		}
		Date now = new Date(System.currentTimeMillis());
		if (!(request.getStart().after(now) && request.getEnd().after(now) &&
				request.getStart().before(request.getEnd()))) {
			return ResponseEntity.badRequest()
					.body(new MessageResponse("Error: You can update only future reservations!"));
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
